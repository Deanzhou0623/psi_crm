package com.roadjava.psi.bean.vo;/*
 *ClassName:SaleOrderVO
 *Description: sale order 传输类
 *@Author:deanzhou
 *@Date:2024-02-10 20:40
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class SaleOrderVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 商品总价
     */
    private BigDecimal totalAmount;
    /**
     * @see com.roadjava.psi.bean.enums.SaleOrderStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;

    /**
     * 买入日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private List<SaleOrderDetailVO> detailList;
}
