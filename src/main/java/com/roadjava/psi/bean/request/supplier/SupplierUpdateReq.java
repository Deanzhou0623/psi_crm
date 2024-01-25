package com.roadjava.psi.bean.request.supplier;/*
 *ClassName:SupplierUpdateReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-24 23:52
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class SupplierUpdateReq {
    @NotNull
    private Long id;
    /**
     * 供应商名
     */
    @NotBlank
    private String name;
}
