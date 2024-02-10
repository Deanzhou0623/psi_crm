package com.roadjava.psi.bean.entity;/*
 *ClassName:ReturnGoodsDetailDO
 *Description: 退货详细信息
 *@Author:deanzhou
 *@Date:2024-01-27 20:26
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("return_goods_detail")
public class ReturnGoodsDetailDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * return_goods表主键
     */
    private Long retId;
    /**
     * 退货批次号
     */
    private String retNo;
    /**
     * 商品表的主键
     */
    private Long goodsId;
    /**
     * 买入价格
     */
    private BigDecimal returnPrice;
    /**
     * 采购数量
     */
    private Integer num;
    /**
     * 供应商表的主键
     */
    private Long supplierId;

}
