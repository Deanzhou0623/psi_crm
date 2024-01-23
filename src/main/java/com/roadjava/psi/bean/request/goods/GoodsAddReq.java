package com.roadjava.psi.bean.request.goods;/*
 *ClassName:GoodsAddReq
 *Description: 商品添加请求对象
 *@Author:deanzhou
 *@Date:2024-01-21 22:27
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class GoodsAddReq {
    /**
     * 商品名称
     */
    @NotBlank
    private String name;
    /**
     * 产地
     */
    private String bornPlace;
    /**
     * 销售价
     */
    @NotNull
    private BigDecimal salePrice;
    /**
     * 单位
     */
    @NotBlank
    private String unit;
    /**
     * 规格
     */
    @NotBlank
    private String specifications;
}
