package com.roadjava.psi.bean.request.supplier;/*
 *ClassName:SupplierSearchReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-24 23:51
 *@Param: BaseSearchReq$ $
 *@Param: BaseSearchReq$ $
 *@Return: $
 */

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

@Data
public class SupplierSearchReq extends BaseSearchReq {
    /**
     * 供应商名字
     */
    private String name;
}
