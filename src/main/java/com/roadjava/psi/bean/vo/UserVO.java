package com.roadjava.psi.bean.vo;/*
 *ClassName:UserVO
 *Description: user 包装类
 *@Author:deanzhou
 *@Date:2024-01-15 21:24
 */

import lombok.Data;

@Data
public class UserVO {
    /**
     * 系统编号
     */
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 角色id
     * @see com.roadjava.psi.bean.enums.RoleEnum
     */
    private Integer roleId;
    /**
     * 角色名称
     */
    private String roleName;
}
