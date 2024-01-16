package com.roadjava.psi.bean.request.purchase;/*
 *ClassName:PurchaseSearchReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-15 21:40
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.roadjava.psi.bean.vo.PurchaseDetailVO;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PurchaseSearchReq {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 进货批次号
     */
    private String purchaseNo;

    /**
     * 经办人id
     */
    private Long operatorId;
    /**
     * 操作人姓名
     */
    private String operatorName;
    /**
     * @see com.roadjava.psi.bean.enums.PurchaseStatusEnum
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
    private Date purchaseDate;
    /**
     * 进货明细列表
     */
    private List<PurchaseDetailVO> detailList;
}
