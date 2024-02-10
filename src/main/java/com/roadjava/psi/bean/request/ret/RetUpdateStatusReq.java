package com.roadjava.psi.bean.request.ret;/*
 *ClassName:RetUpdateStatusReq
 *Description: 返回更新
 *@Author:deanzhou
 *@Date:2024-01-28 15:51
 */

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RetUpdateStatusReq {
    @NotNull
    private Long id;
    /**
     * 0:待审核 1:审核通过 2 审核拒绝
     */
    @NotNull
    private Integer status;
}
