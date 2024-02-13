package com.roadjava.psi.bean.request.refund;/*
 *ClassName:RefundSearchReq
 *Description: 销售订单查询入参
 *@Author:deanzhou
 *@Date:2024-02-10 15:59
 */

import lombok.Data;

@Data
public class RefundSearchReq {
    private String fuzzyRefundNo;
}
