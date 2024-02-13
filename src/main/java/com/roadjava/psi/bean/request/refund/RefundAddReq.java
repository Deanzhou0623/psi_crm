package com.roadjava.psi.bean.request.refund;/*
 *ClassName:RefundAddReq
 *Description: refund 添加请求对象
 *@Author:deanzhou
 *@Date:2024-02-10 15:55
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class RefundAddReq {
    @NotEmpty
    private List<String> orderNoList;
}
