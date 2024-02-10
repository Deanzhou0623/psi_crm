package com.roadjava.psi.service.impl;/*
 *ClassName:ReturnGoodsServiceImpl
 *Description:    ReturnGoodsService 实现类
 *@Author:deanzhou
 *@Date:2024-01-28 15:59
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.bean.entity.ReturnGoodsDO;
import com.roadjava.psi.bean.entity.ReturnGoodsDetailDO;
import com.roadjava.psi.bean.enums.PurchaseStatusEnum;
import com.roadjava.psi.bean.enums.RetStatusEnum;
import com.roadjava.psi.bean.request.ret.RetUpdateStatusReq;
import com.roadjava.psi.bean.request.ret.ReturnAddReq;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.PurchaseDetailVO;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.ReturnGoodsDetailVO;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.context.UserContext;
import com.roadjava.psi.ex.BizException;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.mapper.PurchaseMapper;
import com.roadjava.psi.mapper.ReturnGoodsDetailMapper;
import com.roadjava.psi.mapper.ReturnGoodsMapper;
import com.roadjava.psi.service.ReturnGoodsService;
import com.roadjava.psi.utils.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ReturnGoodsServiceImpl implements ReturnGoodsService {
    @Resource
    private ReturnGoodsMapper returnGoodsMapper;
    @Resource
    private ReturnGoodsDetailMapper returnGoodsDetailMapper;
    @Resource
    private PurchaseMapper purchaseMapper;
    @Resource
    private GoodsMapper goodsMapper;

    /*
    * add
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(ReturnAddReq req) {
        List<String> list = req.getPurchaseNoList();
        for (String purchaseNo:list) {
            // 查询对应的进货单
            PurchaseDO purchaseQuery = new PurchaseDO();
            purchaseQuery.setPurchaseNo(purchaseNo);
            PurchaseVO purchaseVO = purchaseMapper.selectWithDetail(purchaseQuery);
            if (!PurchaseStatusEnum.AGREED.getStatus().equals(purchaseVO.getStatus())) {
                throw new BizException("只有审核通过的进货单才能进行退货,进货单:"+purchaseVO.getPurchaseNo()+"状态不满足");
            }
            //退货主表
            ReturnGoodsDO returnGoods = new ReturnGoodsDO();
            returnGoods.setPurchaseNo(purchaseVO.getPurchaseNo());
            returnGoods.setRetNo(NoUtil.getNo(Constants.PRE_RET));
            returnGoods.setOperatorId(UserContext.get().getId());
            returnGoods.setReturnDate(new Date());
            returnGoodsMapper.insert(returnGoods);
            //退货从表
            // 退货从表
            for (PurchaseDetailVO purchaseDetail : purchaseVO.getDetailList()) {
                // 构造退货从表
                ReturnGoodsDetailDO returnGoodsDetail = new ReturnGoodsDetailDO();
                returnGoodsDetail.setRetId(returnGoods.getId());
                returnGoodsDetail.setRetNo(returnGoods.getRetNo());
                returnGoodsDetail.setGoodsId(purchaseDetail.getGoodsId());
                returnGoodsDetail.setReturnPrice(purchaseDetail.getBuyPrice());
                returnGoodsDetail.setNum(purchaseDetail.getNum());
                returnGoodsDetail.setSupplierId(purchaseDetail.getSupplierId());
                returnGoodsDetailMapper.insert(returnGoodsDetail);
            }

        }

    }

    /*
    * 退货更新状态
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(RetUpdateStatusReq req) {
        //1. 找到对应的商品
        ReturnGoodsDO returnGoodsDO = returnGoodsMapper.selectById(req.getId());
        returnGoodsDO.setId(req.getId());
        // approved or not 
        returnGoodsDO.setStatus(req.getStatus());
        returnGoodsMapper.updateById(returnGoodsDO);
        //审核通过时扣减商品库存
        if (RetStatusEnum.AGREED.getStatus().equals(req.getStatus())) {
            // 查到该退货单的商品明细
            LambdaQueryWrapper<ReturnGoodsDetailDO> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(ReturnGoodsDetailDO::getRetId,req.getId());
            List<ReturnGoodsDetailDO> detailDOList = returnGoodsDetailMapper.selectList(queryWrapper);
            for (ReturnGoodsDetailDO returnGoodsDetailDO : detailDOList) {
                //现在的库存
                Integer retGoodsNum = returnGoodsDetailDO.getNum();
                // 并分别扣减库存
                GoodsDO existedGoods = goodsMapper.selectById(returnGoodsDetailDO.getGoodsId());
                if(existedGoods == null) {
                    continue;
                }
                int remainStock = existedGoods.getStock() - retGoodsNum;
                if (remainStock < 0) {
                    throw new BizException("审核通过失败:库存不够,无法进行退货");
                }
                existedGoods.setStock(remainStock);
                goodsMapper.updateById(existedGoods);
            }
            // 更新进货单状态为已退货
            PurchaseDO purchaseToUpdate = new PurchaseDO();
            purchaseToUpdate.setStatus(PurchaseStatusEnum.RET_ED.getStatus());
            LambdaUpdateWrapper<PurchaseDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(PurchaseDO::getPurchaseNo,returnGoodsDO.getPurchaseNo());
            purchaseMapper.update(purchaseToUpdate,updateWrapper);
        }

    }

    /*
    * 加载表格
    * */
    @Override
    public ResultVO<TableResult<ReturnGoodsVO>> loadTable(ReturnSearchReq req) {
        PageHelper.startPage(req);
        List<ReturnGoodsVO> voList= returnGoodsMapper.selectRetList(req);
        if (CollectionUtils.isEmpty(voList)){
            return ResultVO.buildEmptySuccess();
        }
        List<String> retNos=voList.stream().
                map(ReturnGoodsVO::getRetNo).
                collect(Collectors.toList());
        List<ReturnGoodsDetailVO> detailList = returnGoodsDetailMapper.selectByRetNo(retNos);
        // 按退货号分组,每组即对应的明细列表
        Map<String, List<ReturnGoodsDetailVO>> retNo2DetailListMap = detailList.stream()
                .collect(Collectors.groupingBy(ReturnGoodsDetailVO::getRetNo));
        voList.forEach(vo -> {
            Optional.ofNullable(RetStatusEnum.getEnum(vo.getStatus()))
                    .ifPresent(e -> vo.setStatusDesc(e.getDesc()));
            vo.setDetailList(retNo2DetailListMap.get(vo.getRetNo()));
        });
        // 设置分页信息
        PageInfo<ReturnGoodsVO> pageInfo = new PageInfo<>(voList);
        TableResult<ReturnGoodsVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageInfo.getTotal());
        tableResult.setRows(pageInfo.getList());
        return ResultVO.buildSuccess(tableResult);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
         //1. 查到具体删除对象
        ReturnGoodsDO returnGoodsDO = returnGoodsMapper.selectById(id);
        //2. 查到该商品对具体明细
        LambdaQueryWrapper<ReturnGoodsDetailDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReturnGoodsDetailDO::getRetId,id);
        //3. 查询批准
        if (RetStatusEnum.AGREED.getStatus().equals(returnGoodsDO.getStatus())){
            //查询每一条的记录
            List<ReturnGoodsDetailDO> detailDOList = returnGoodsDetailMapper.selectList(queryWrapper);
            for (ReturnGoodsDetailDO returnGoodsDetailDO : detailDOList) {
                //查询对应的库存
                GoodsDO existedGoods = goodsMapper.selectById(returnGoodsDetailDO.getGoodsId());
                //是否为null
                if (existedGoods ==null){
                    continue;
                }
                //更新库存 将退货详情表的库存情况 更新到goods表中
                existedGoods.setStock(existedGoods.getStock()+returnGoodsDetailDO.getNum());
                goodsMapper.updateById(existedGoods);
                
            }
            //更新状态单 退货单退货 -更新进货单情况
            PurchaseDO purchaseToUpdate = new PurchaseDO();
            purchaseToUpdate.setStatus(PurchaseStatusEnum.AGREED.getStatus());
            LambdaUpdateWrapper<PurchaseDO> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(PurchaseDO::getPurchaseNo,returnGoodsDO.getPurchaseNo());
            purchaseMapper.update(purchaseToUpdate,updateWrapper);
            
        }
        //删除明细
        returnGoodsDetailMapper.delete(queryWrapper);
        returnGoodsMapper.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByPurchaseNo(String purchaseNo) {
        LambdaQueryWrapper<ReturnGoodsDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ReturnGoodsDO::getPurchaseNo,purchaseNo);
        ReturnGoodsDO returnGoodsDO = returnGoodsMapper.selectOne(queryWrapper);
        if (returnGoodsDO !=null){
            returnGoodsMapper.deleteById(returnGoodsDO.getId());
        }

    }
}
