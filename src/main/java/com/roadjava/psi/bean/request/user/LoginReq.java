package com.roadjava.psi.bean.request.user;/*
 *ClassName:LoginReq
 *Description: 登入请求
 *@Author:deanzhou
 *@Date:2024-01-22 22:12
 */

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginReq {
    /**
     * 用户名
     */
    @NotBlank
//    @TableField("user_name")
    private String userName;
    /**
     * 密码
     */
    @NotBlank
    private String pwd;
}
