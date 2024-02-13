package com.roadjava.psi.mapper;/*
 *ClassName:SaleOrderMapper
 *Description: 
 *@Author:deanzhou
 *@Date:2024-02-10 20:02
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.SaleOrderDO;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderVO;

import java.util.List;

public interface SaleOrderMapper extends BaseMapper<SaleOrderDO> {
    List<SaleOrderVO> selectOrderList(SaleOrderSearchReq req);
}
