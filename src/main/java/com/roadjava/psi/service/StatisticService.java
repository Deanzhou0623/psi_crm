package com.roadjava.psi.service;/*
 *ClassName:StatisticService
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-12 22:07
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.roadjava.psi.bean.request.statistics.ChartReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.statistics.BarVO;
import com.roadjava.psi.bean.vo.statistics.LineVO;
import com.roadjava.psi.bean.vo.statistics.PieVO;

public interface StatisticService {

    ResultVO<BarVO> loadSaleNum4Goods(ChartReq req);

    ResultVO<BarVO> loadSaleAmount4Goods(ChartReq req);

    ResultVO<LineVO> loadTrend(ChartReq req);

    ResultVO<PieVO> loadRet4Supplier(ChartReq req);
}
