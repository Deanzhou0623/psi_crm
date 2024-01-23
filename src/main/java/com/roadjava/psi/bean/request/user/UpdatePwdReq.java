package com.roadjava.psi.bean.request.user;/*
 *ClassName:UpdatePwdReq
 *Description: udpate pwd request
 *@Author:deanzhou
 *@Date:2024-01-22 22:25
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UpdatePwdReq {
    /**
     * 系统编号
     */
    @NotNull
    private Long id;

    /**
     * 原始密码
     */
    @NotBlank
    private String oldPwd;

    /**
     * 修改后的密码
     */
    @NotBlank
    private String newPwd;
}
