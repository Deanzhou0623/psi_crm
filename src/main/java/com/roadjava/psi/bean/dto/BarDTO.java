package com.roadjava.psi.bean.dto;/*
 *ClassName:BarDTO
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-12 22:04
 */

import lombok.Data;

@Data
public class BarDTO {
    /**
     * 如: goodsName
     */
    private String xAxisName;
    /**
     * 如: 销量
     */
    private Integer yAxisValueInt;
    /**
     * 如: 金额
     */
    private Double yAxisValueDouble;
}
