package com.roadjava.psi.bean.request.ret;/*
 *ClassName:ReturnAddReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-28 15:50
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class ReturnAddReq {
    /**
     * 进货id
     */
    @NotEmpty
    private List<String> purchaseNoList;
}
