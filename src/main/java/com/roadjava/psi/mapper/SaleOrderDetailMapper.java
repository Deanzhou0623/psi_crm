package com.roadjava.psi.mapper;/*
 *ClassName:SaleOrderDetailMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 22:38
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.SaleOrderDetailDO;
import com.roadjava.psi.bean.vo.SaleOrderDetailVO;

import java.util.List;

public interface SaleOrderDetailMapper extends BaseMapper<SaleOrderDetailDO> {
    List<SaleOrderDetailVO> selectByOrderIds(List<Long> orderIds);
    
}
