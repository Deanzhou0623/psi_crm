package com.roadjava.psi.bean.vo;/*
 *ClassName:RoleVO
 *Description: 角色
 *@Author:deanzhou
 *@Date:2024-01-24 23:59
 */

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class RoleVO {
    private Integer roleId;
    private String roleName;
}
