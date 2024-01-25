package com.roadjava.psi.bean.request.supplier;/*
 *ClassName:SupplierAddReq
 *Description: 请求对象
 *@Author:deanzhou
 *@Date:2024-01-24 23:49
 */


import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SupplierAddReq {
    /**
     * 供应商名
     */
    @NotBlank
    private String name;
}
