package com.roadjava.psi.bean.entity;/*
 *ClassName:SaleOrderDO
 *Description: 商品销售订单
 *@Author:deanzhou
 *@Date:2024-02-10 20:15
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("sale_order")
public class SaleOrderDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
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
     * 创建日期
     */
    private Date createTime;
}
