package com.roadjava.psi.bean.vo.statistics;/*
 *ClassName:PieVO
 *Description: 柱状图视图对象
 *@Author:deanzhou
 *@Date:2024-01-15 21:04
 */

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PieVO {
    /**
     * {value: 1048, name: '搜索引擎'}
     */
    private List<Map<String,Object>> seriesData;
}
