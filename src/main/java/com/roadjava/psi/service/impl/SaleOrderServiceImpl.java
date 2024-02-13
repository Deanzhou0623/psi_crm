package com.roadjava.psi.service.impl;/*
 *ClassName:SaleOrderServiceImpl
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 22:35
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.entity.SaleOrderDetailDO;
import com.roadjava.psi.bean.enums.SaleOrderStatusEnum;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderAddReq;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderDetailReq;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderDetailVO;
import com.roadjava.psi.bean.vo.SaleOrderVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.SaleOrderConvert;
import com.roadjava.psi.ex.BizException;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.mapper.SaleOrderDetailMapper;
import com.roadjava.psi.mapper.SaleOrderMapper;
import com.roadjava.psi.service.SaleOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SaleOrderServiceImpl implements SaleOrderService {
    @Resource
    private SaleOrderMapper saleOrderMapper;
    @Resource
    private SaleOrderDetailMapper saleOrderDetailMapper;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private SaleOrderConvert saleOrderConvert;
    @Override
    /*
    * detailByOrderNo
    * */
    public SaleOrderVO detailByOrderNo(String orderNo) {
        SaleOrderSearchReq req = new SaleOrderSearchReq();
        req.setOrderNo(orderNo);
        return selectWithDetail(req).get(0);
    }
    private List<SaleOrderVO> selectWithDetail(SaleOrderSearchReq req) {
        List<SaleOrderVO> voList = saleOrderMapper.selectOrderList(req);
        if (CollectionUtils.isEmpty(voList)) {
            return new ArrayList<>();
        }
        List<Long> orderIds = voList.stream()
                .map(SaleOrderVO::getId)
                .collect(Collectors.toList());
        List<SaleOrderDetailVO> detailList = saleOrderDetailMapper.selectByOrderIds(orderIds);
        // 按退货号分组,每组即对应的明细列表
        Map<Long, List<SaleOrderDetailVO>> retNo2DetailListMap = detailList.stream()
                .collect(Collectors.groupingBy(SaleOrderDetailVO::getOrderId));
        voList.forEach(vo -> vo.setDetailList(retNo2DetailListMap.get(vo.getId())));
        return voList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SaleOrderAddReq req) {
        SaleOrderDO entity = saleOrderConvert.addReq2DO(req);
        saleOrderMapper.insert(entity);

        //查到商品并扣减库存
        for (SaleOrderDetailReq detailReq : req.getDetailList()) {
            Long goodsId = detailReq.getGoodsId();
            GoodsDO existedGoods = goodsMapper.selectById(goodsId);
            if (existedGoods.getStock() < detailReq.getNum()) {
                throw new BizException("商品:"+existedGoods.getName()+"库存不足,无法生成销售单");
            }
            existedGoods.setStock(existedGoods.getStock() - detailReq.getNum());
            goodsMapper.updateById(existedGoods);
            //插入销售明细
            SaleOrderDetailDO detailDO = saleOrderConvert.detailAddReq2DO(detailReq);
            detailDO.setOrderId(entity.getId());
            saleOrderDetailMapper.insert(detailDO);
        }
    }

    @Override
    public ResultVO<TableResult<SaleOrderVO>> loadTable(SaleOrderSearchReq req) {
        PageHelper.startPage(req);
        List<SaleOrderVO> voList = this.selectWithDetail(req);
        if (CollectionUtils.isEmpty(voList)) {
            return ResultVO.buildEmptySuccess();
        }
        populateBean(voList);
        // 设置分页信息
        PageInfo<SaleOrderVO> pageInfo = new PageInfo<>(voList);
        TableResult<SaleOrderVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageInfo.getTotal());
        tableResult.setRows(pageInfo.getList());
        return ResultVO.buildSuccess(tableResult);
    }
    /*
    *  显示对应的状态
    * */
    private void populateBean(List<SaleOrderVO> voList) {
        if (CollectionUtils.isNotEmpty(voList)) {
            voList.forEach(vo -> Optional.ofNullable(SaleOrderStatusEnum.getEnum(vo.getStatus()))
                    .ifPresent(e -> vo.setStatusDesc(e.getDesc())));
        }
    }
}
