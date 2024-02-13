package com.roadjava.psi.bean.request.statistics;/*
 *ClassName:ChartReq
 *Description: 
 *@Author:deanzhou
 *@Date:2024-02-12 22:09
 */

import lombok.Data;

import java.util.List;
@Data
public class ChartReq {
    private Integer graduateYear;
    private List<Long> majorIds;
    private List<Long> goodsIds;
    private String startDate;
}
