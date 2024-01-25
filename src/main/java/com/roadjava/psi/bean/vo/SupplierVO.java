package com.roadjava.psi.bean.vo;/*
 *ClassName:SupplierVO
 *Description: 返回给前端的供应商对象
 *@Author:deanzhou
 *@Date:2024-01-24 23:44
 */

import lombok.Data;

@Data
public class SupplierVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 供应商名
     */
    private String name;
}
