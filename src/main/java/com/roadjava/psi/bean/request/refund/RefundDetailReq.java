package com.roadjava.psi.bean.request.refund;/*
 *ClassName:RefundDetailReq
 *Description: 退款详情请求
 *@Author:deanzhou
 *@Date:2024-02-10 15:57
 */

import lombok.Data;

import java.math.BigDecimal;

@Data
public class RefundDetailReq {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 卖出价格
     */
    private BigDecimal price;
    /**
     * 购买数量
     */
    private Integer num;
}
