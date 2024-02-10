package com.roadjava.psi.bean.request.ret;/*
 *ClassName:ReturnSearchReq
 *Description: 退货查询入参
 *@Author:deanzhou
 *@Date:2024-01-28 15:50
 */

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

@Data
public class ReturnSearchReq extends BaseSearchReq {
    private String fuzzyRetNo;
    private Integer status;
}
