package com.roadjava.psi.convert;/*
 *ClassName:saleOrderConvert
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:44
 */

import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.entity.SaleOrderDetailDO;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderAddReq;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderDetailReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SaleOrderConvert {
    public SaleOrderDO addReq2DO(SaleOrderAddReq req) {
        SaleOrderDO entity = new SaleOrderDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }

    public SaleOrderDetailDO detailAddReq2DO(SaleOrderDetailReq saleOrderDetailReq) {
        SaleOrderDetailDO entity = new SaleOrderDetailDO();
        BeanUtils.copyProperties(saleOrderDetailReq,entity);
        return entity;
    }
}
