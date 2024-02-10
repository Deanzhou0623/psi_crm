package com.roadjava.psi.mapper;/*
 *ClassName:ReturnGoodsMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-30 21:40
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.ReturnGoodsDO;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;

import java.util.List;

public interface ReturnGoodsMapper extends BaseMapper<ReturnGoodsDO> {
    List<ReturnGoodsVO> selectRetList(ReturnSearchReq req);
}
