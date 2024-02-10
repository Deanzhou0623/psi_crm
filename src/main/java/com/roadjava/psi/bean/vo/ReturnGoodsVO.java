package com.roadjava.psi.bean.vo;/*
 *ClassName:ReturnGoodsVO
 *Description: return good vo 
 *@Author:deanzhou
 *@Date:2024-01-28 15:54
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReturnGoodsVO {
    private Long id;
    /**
     * 进货批次号
     */
    private String purchaseNo;
    /**
     * 退货号
     */
    private String retNo;
    /**
     * 经办人
     */
    private Long operatorId;
    /**
     * 经办人姓名
     */
    private String operatorName;
    /**
     * @see com.roadjava.psi.bean.enums.RetStatusEnum
     */
    private Integer status;
    /**
     * 状态描述
     */
    private String statusDesc;
    /**
     * 退货日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date returnDate;
    private List<ReturnGoodsDetailVO> detailList;
}
