package com.roadjava.psi.bean.vo;/*
 *ClassName:RefundVO
 *Description: refund 传输类
 *@Author:deanzhou
 *@Date:2024-02-10 19:48
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class RefundVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    private List<RefundDetailVO> detailList;
}
