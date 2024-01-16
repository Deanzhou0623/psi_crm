package com.roadjava.psi.bean.vo.statistics;/*
 *ClassName:BarVO
 *Description: 柱状图视图对象
 *@Author:deanzhou
 *@Date:2024-01-15 21:01
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BarVO {
    /**
     * jackson默认返回的xaxisData
     */
    @JsonProperty("xAxisData")
    private List<String> xAxisData = new ArrayList<>();
    private List<Number> seriesData = new ArrayList<>();
}
