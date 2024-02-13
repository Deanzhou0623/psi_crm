package com.roadjava.psi.mapper;/*
 *ClassName:StatisticMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-12 22:15
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.dto.BarDTO;
import com.roadjava.psi.bean.request.statistics.ChartReq;

import java.util.List;
import java.util.Map;

public interface StatisticMapper {
    List<BarDTO> loadSaleNum4Goods(ChartReq req);

    List<BarDTO> loadSaleAmount4Goods(ChartReq req);

    List<Map<String, Object>> loadTrend(ChartReq req);

    List<Map<String, Object>> supplierRetCount(ChartReq req);
}
