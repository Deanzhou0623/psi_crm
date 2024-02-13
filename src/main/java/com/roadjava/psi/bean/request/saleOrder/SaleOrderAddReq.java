package com.roadjava.psi.bean.request.saleOrder;/*
 *ClassName:SaleOrderAddReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:45
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleOrderAddReq {
    /**
     * 订单号
     */
    @NotBlank
    private String orderNo;
    /**
     * 经办人id
     */
    @NotNull
    private BigDecimal totalAmount;
    /**
     * 买入日期
     * springmvc默认解析的日期格式: yyyy-MM-dd'T'HH:mm:ss.SSSX
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private List<SaleOrderDetailReq> detailList;
}
