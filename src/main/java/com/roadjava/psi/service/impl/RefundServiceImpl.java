package com.roadjava.psi.service.impl;/*
 *ClassName:RefundServiceImpl
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 19:58
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.entity.RefundDO;
import com.roadjava.psi.bean.entity.RefundDetailDO;
import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.enums.SaleOrderStatusEnum;
import com.roadjava.psi.bean.request.refund.RefundAddReq;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundDetailVO;
import com.roadjava.psi.bean.vo.RefundVO;
import com.roadjava.psi.bean.vo.SaleOrderDetailVO;
import com.roadjava.psi.bean.vo.SaleOrderVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.SaleOrderConvert;
import com.roadjava.psi.ex.BizException;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.mapper.RefundDetailMapper;
import com.roadjava.psi.mapper.RefundMapper;
import com.roadjava.psi.mapper.SaleOrderMapper;
import com.roadjava.psi.service.RefundService;
import com.roadjava.psi.service.SaleOrderService;
import com.roadjava.psi.utils.NoUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RefundServiceImpl implements RefundService {
    @Resource
    private RefundMapper refundMapper;
    @Resource
    private SaleOrderService saleOrderService;
    @Resource
    private GoodsMapper goodsMapper;
    @Resource
    private RefundDetailMapper refundDetailMapper;
    @Resource
    private SaleOrderMapper saleOrderMapper;

    /*
    * add 退款对象到表中
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(RefundAddReq req) {
        List<String> orderNoList = req.getOrderNoList();
        for (String orderNo: orderNoList) {
            // 查询对应的订单
            SaleOrderVO saleOrderVO = saleOrderService.detailByOrderNo(orderNo);
            //order finished then can be approved to refund
            if (!SaleOrderStatusEnum.DONE.getStatus().equals(saleOrderVO.getStatus())){
                throw new BizException("只有已完成的销售单才能进行退款,销售单:"+orderNo+"的状态不是已完成");
            }
            // 构造退款主表
            RefundDO refundDO = new RefundDO();
            refundDO.setOrderNo(saleOrderVO.getOrderNo());
            refundDO.setRefundNo(NoUtil.getNo(Constants.PRE_REFUND));
            refundDO.setTotalAmount(saleOrderVO.getTotalAmount());
            refundDO.setCreateTime(new Date());
            refundMapper.insert(refundDO);
            // 退货从表
            for (SaleOrderDetailVO saleOrderDetailVO : saleOrderVO.getDetailList()) {
                //id
                Long goodsId = saleOrderDetailVO.getGoodsId();
                //num 卖出的数量
                Integer saleOrderGoodsNum = saleOrderDetailVO.getNum();
                // 查到该商品并增加库存
                GoodsDO existedGoods = goodsMapper.selectById(goodsId);
                existedGoods.setStock(existedGoods.getStock() + saleOrderGoodsNum);
                goodsMapper.updateById(existedGoods);
                // 构造退款从表
                RefundDetailDO refundDetailDO = new RefundDetailDO();
                refundDetailDO.setRefundId(refundDO.getId());
                refundDetailDO.setGoodsId(goodsId);
                refundDetailDO.setRefundPrice(saleOrderDetailVO.getSalePrice());
                refundDetailDO.setNum(saleOrderGoodsNum);
                refundDetailMapper.insert(refundDetailDO);
            }
            // 更新进货单状态为已退货
            SaleOrderDO saleOrderToUpdate = new SaleOrderDO();
            saleOrderToUpdate.setStatus(SaleOrderStatusEnum.REFUND_ED.getStatus());
            saleOrderToUpdate.setId(saleOrderVO.getId());
            saleOrderMapper.updateById(saleOrderToUpdate);
        }
    }

    @Override
    public ResultVO<TableResult<RefundVO>> loadTable(RefundSearchReq req) {
        PageHelper.startPage(req);
        List<RefundVO> voList= refundMapper.selectRefundList(req);
        if (CollectionUtils.isEmpty(voList)){
            return ResultVO.buildEmptySuccess();
        }
        //根据 refund id 过滤
         List<Long> refundIds=voList.stream()
                .map(RefundVO::getId)
                .collect(Collectors.toList());
        //根据  refundIds 在detail中找到对应到数据
        List<RefundDetailVO> detailList=refundDetailMapper.selectByRefundIds(refundIds);
        //按退货id(前缀+时间)分组,每组即对应的明细列表
        Map<Long, List<RefundDetailVO>> retNo2DetailListMap = detailList
                .stream()
                .collect(Collectors.groupingBy(RefundDetailVO::getRefundId));

        voList.forEach(vo -> vo.setDetailList(retNo2DetailListMap.get(vo.getId())));
        // 设置表格结果并返回
        PageInfo<RefundVO> pageInfo = new PageInfo<>(voList);
        TableResult<RefundVO> tableResult = new TableResult<>();
        tableResult.setTotal((pageInfo.getTotal()));
        tableResult.setRows(pageInfo.getList());
        return ResultVO.buildSuccess(tableResult);
    }
}
