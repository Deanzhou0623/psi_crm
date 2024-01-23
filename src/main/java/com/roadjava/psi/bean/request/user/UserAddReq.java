package com.roadjava.psi.bean.request.user;/*
 *ClassName:UserAddReq
 *Description: user add request
 *@Author:deanzhou
 *@Date:2024-01-22 22:24
 */

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserAddReq {
    /**
     * 用户名
     */
    @NotBlank
    private String userName;
    /**
     * 密码
     */
    @NotBlank
    private String pwd;
    /**
     * 角色id
     * @see com.roadjava.psi.bean.enums.RoleEnum
     */
    @NotNull
    private Integer roleId;
}
