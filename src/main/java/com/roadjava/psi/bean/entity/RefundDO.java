package com.roadjava.psi.bean.entity;/*
 *ClassName:RefundDO
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:05
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("refund")
public class RefundDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单表主键
     */
    private String orderNo;
    /**
     * 退款编号
     */
    private String refundNo;
    /**
     * 退款总金额
     */
    private BigDecimal totalAmount;
    /**
     * 日期
     */
    private Date createTime;
}
