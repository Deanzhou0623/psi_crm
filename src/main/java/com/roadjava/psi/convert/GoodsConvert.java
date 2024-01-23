package com.roadjava.psi.convert;/*
 *ClassName:GoodsConvert
 *Description: 请求转化成对象
 *@Author:deanzhou
 *@Date:2024-01-21 22:31
 */

import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GoodsConvert {
    public GoodsDO addReq2DO(GoodsAddReq req) {
        GoodsDO entity = new GoodsDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }
}
