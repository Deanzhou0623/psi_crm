package com.roadjava.psi.bean.request.saleOrder;/*
 *ClassName:SaleOrderDetailReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:47
 */

import lombok.Data;

import java.math.BigDecimal;
@Data
public class SaleOrderDetailReq {
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 卖出价格
     */
    private BigDecimal salePrice;
    /**
     * 购买数量
     */
    private Integer num;
}
