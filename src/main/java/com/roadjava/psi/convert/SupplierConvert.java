package com.roadjava.psi.convert;/*
 *ClassName:SupplierConvert
 *Description: supplier convert do to vo 
 *@Author:deanzhou
 *@Date:2024-01-25 00:08
 */

import com.roadjava.psi.bean.entity.SupplierDO;
import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class SupplierConvert {
    public SupplierDO addReq2DO(SupplierAddReq req) {
        SupplierDO entity = new SupplierDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }

    public SupplierDO updateReq2DO(SupplierUpdateReq req) {
        SupplierDO entity = new SupplierDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }


    public SupplierVO entity2VO(SupplierDO entity) {
        SupplierVO vo = new SupplierVO();
        BeanUtils.copyProperties(entity, vo);
        return vo;
    }
}
