package com.roadjava.psi.bean.entity;/*
 *ClassName:SaleOrderDetailDO
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:21
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("sale_order_detail")
public class SaleOrderDetailDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 订单表外键
     */
    private Long orderId;
    /**
     * 商品id
     */
    private Long goodsId;
    /**
     * 卖出价格
     */
    private BigDecimal salePrice;
    /**
     * 购买数量
     */
    private Integer num;
}
