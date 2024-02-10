package com.roadjava.psi.mapper;/*
 *ClassName:ReturnGoodsDetailMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-30 21:41
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.ReturnGoodsDetailDO;
import com.roadjava.psi.bean.vo.ReturnGoodsDetailVO;

import java.util.List;

public interface ReturnGoodsDetailMapper extends BaseMapper<ReturnGoodsDetailDO> {
    List<ReturnGoodsDetailVO> selectByRetNo(List<String> retNos);
}
