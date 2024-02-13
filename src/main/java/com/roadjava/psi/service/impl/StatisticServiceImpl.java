package com.roadjava.psi.service.impl;/*
 *ClassName:StatisticServiceImpl
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-12 22:14
 */

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.roadjava.psi.bean.dto.BarDTO;
import com.roadjava.psi.bean.request.statistics.ChartReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.statistics.BarVO;
import com.roadjava.psi.bean.vo.statistics.LineVO;
import com.roadjava.psi.bean.vo.statistics.PieVO;
import com.roadjava.psi.mapper.StatisticMapper;
import com.roadjava.psi.service.StatisticService;
import com.roadjava.psi.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatisticServiceImpl implements StatisticService {

    @Resource
    private StatisticMapper statisticMapper;
    /*
    * 近30天销量最高的
    * */
    @Override
    public ResultVO<BarVO> loadSaleNum4Goods(ChartReq req) {
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        req.setStartDate(dateList.get(0));
        List<BarDTO> list = statisticMapper.loadSaleNum4Goods(req);
        BarVO vo = new BarVO();
        list.forEach(co -> {
            vo.getXAxisData().add(co.getXAxisName());
            vo.getSeriesData().add(co.getYAxisValueInt());
        });
        return ResultVO.buildSuccess(vo);
    }

    @Override
    public ResultVO<BarVO> loadSaleAmount4Goods(ChartReq req) {
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        req.setStartDate(dateList.get(0));
        /*
        * 销售数据
        * */
        List<BarDTO> list = statisticMapper.loadSaleAmount4Goods(req);
        BarVO vo = new BarVO();
        list.forEach(co -> {
            vo.getXAxisData().add(co.getXAxisName());
            vo.getSeriesData().add(co.getYAxisValueDouble());
        });
        return ResultVO.buildSuccess(vo);
    }


    /*
     30 天的进价趋势
     */
    @Override
    public ResultVO<LineVO> loadTrend(ChartReq req) {
        if (CollectionUtils.isEmpty(req.getGoodsIds())) {
            return ResultVO.buildSuccess(new LineVO());
        }
        // 近30天
        List<String> dateList = DateUtil.getDateList(30);
        req.setStartDate(dateList.get(0));
        List<Map<String, Object>> maps = statisticMapper.loadTrend(req);
        //相应的名字
        List<String> goodsNames = maps.stream().map(map-> map.get("goodsName")
                .toString())
                .distinct()
                .collect(Collectors.toList());
        LineVO lineVO = new LineVO();
        // 名字对应图表的下面的label 添加对应数据
        lineVO.setLegendData(goodsNames);
        lineVO.setXAxisData(dateList);
        List<Map<String, Object>> series = new ArrayList<>();
        Map<String, List<Map<String, Object>>> goodsNameMap = maps.stream()
                .collect(Collectors.groupingBy(map -> map.get("goodsName")
                        .toString()));

        for (String goodsName : goodsNames) {
            Map<String, Object> oneSeries = new HashMap<>();
            oneSeries.put("name", goodsName);
            oneSeries.put("type", "line");
            oneSeries.put("smooth", true);
            /*平均进价
            * */
            List<Map<String, Object>> goodsDatePriceList = goodsNameMap.get(goodsName);
            // 按日期转为map结构
            Map<String, Double> datePriceMap = goodsDatePriceList.stream()
                    .collect(Collectors.toMap(m -> m.get("purchaseDate").toString(),
                            m -> Double.valueOf(m.get("buyPrice").toString()),
                            (a, b) -> b)
                    );
            // 近30天进价
            List<Double> oneSeriesData = new ArrayList<>();
            for (String day : dateList) {
                // 如果该商品在该天没有值,则补0,不然数据就错位了
                oneSeriesData.add(datePriceMap.getOrDefault(day, 0.0d));
            }
            oneSeries.put("data", oneSeriesData);
            series.add(oneSeries);
        }
        lineVO.setSeries(series);
        return ResultVO.buildSuccess(lineVO);
    }

    /*
    * 供应商退货占比统计
    * */
    @Override
    public ResultVO<PieVO> loadRet4Supplier(ChartReq req) {
        // [{"供应商1":1},{"供应商2":2}]
        List<Map<String, Object>> supplierRetCount = statisticMapper.supplierRetCount(req);

        int totalRetCount = supplierRetCount.stream()
                .mapToInt(map -> MapUtils.getInteger(map,"cnt"))
                .sum();

        for (Map<String, Object> map : supplierRetCount) {
            // 退货占比=该供应商退货次数/退货总数
            BigDecimal ratio = new BigDecimal(MapUtils.getString(map,"cnt"))
                    .divide(new BigDecimal(totalRetCount),
                            2, BigDecimal.ROUND_HALF_UP);
            map.put("value", ratio.multiply(new BigDecimal(100)));
        }
        PieVO vo = new PieVO();
        vo.setSeriesData(supplierRetCount);
        return ResultVO.buildSuccess(vo);
    }
}
