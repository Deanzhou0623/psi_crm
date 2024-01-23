package com.roadjava.psi.mapper;/*
 *ClassName:GoodsMapper
 *Description:  实现goods 与 后端对连接
 *@Author:deanzhou
 *@Date:2024-01-21 22:24
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.statistics.GoodsVO;

import java.util.List;

public interface GoodsMapper extends BaseMapper<GoodsDO> {

    List<GoodsVO> selectGoodsList(GoodsSearchReq req);
}
