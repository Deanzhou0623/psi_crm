package com.roadjava.psi.bean.request.purchase;/*
 *ClassName:PurchaseUpdateStatusReq
 *Description: 进货更新请求
 *@Author:deanzhou
 *@Date:2024-01-27 21:28
 */

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class PurchaseUpdateStatusReq {
    @NotNull
    private Long id;
    /**
     * 0:待审核 1:审核通过 2 审核拒绝
     */
    @NotNull
    private Integer status;
}
