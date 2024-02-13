package com.roadjava.psi.bean.vo;/*
 *ClassName:RefundDetailVO
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 19:49
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("refund_detail")
public class RefundDetailVO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 退款表主键
     */
    private Long refundId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 商品名
     */
    private String goodsName;
    /**
     * 卖出价格
     */
    private BigDecimal refundPrice;
    /**
     * 购买数量
     */
    private Integer num;
}
