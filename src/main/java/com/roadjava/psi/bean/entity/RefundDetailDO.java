package com.roadjava.psi.bean.entity;/*
 *ClassName:RefundDetailDO
 *Description: 退款明细表
 *@Author:deanzhou
 *@Date:2024-02-10 20:10
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("refund_detail")
public class RefundDetailDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 退款主表refund的主键
     */
    private Long refundId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 退款价格
     */
    private BigDecimal refundPrice;
    /**
     * 购买数量
     */
    private Integer num;
}
