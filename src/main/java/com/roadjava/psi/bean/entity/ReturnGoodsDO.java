package com.roadjava.psi.bean.entity;/*
 *ClassName:ReturnGoodsDO
 *Description: 退货信息
 *@Author:deanzhou
 *@Date:2024-01-27 20:24
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("return_goods")
public class ReturnGoodsDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 进货号
     */
    private String purchaseNo;
    /**
     * 退货批次号
     */
    private String retNo;
    /**
     * 经办人
     */
    private Long operatorId;
    /**
     * 0:待审核 1:审核通过
     */
    private Integer status;
    /**
     * 退货日期
     */
    private Date returnDate;
}
