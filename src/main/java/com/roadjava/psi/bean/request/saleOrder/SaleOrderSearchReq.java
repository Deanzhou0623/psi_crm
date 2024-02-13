package com.roadjava.psi.bean.request.saleOrder;/*
 *ClassName:SaleOrderSearchReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:47
 */

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

@Data
public class SaleOrderSearchReq extends BaseSearchReq {
    private String orderNo;
    /**
     * 模糊查询的订单号
     */
    private String fuzzyOrderNo;
}
