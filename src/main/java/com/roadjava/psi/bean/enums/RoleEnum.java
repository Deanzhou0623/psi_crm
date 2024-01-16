package com.roadjava.psi.bean.enums;/*
 *ClassName:RoleEnum
 *Description: 用户角色
 *@Author:deanzhou
 *@Date:2024-01-15 21:25
 */

public enum RoleEnum {
    ADMIN(1,"超级管理员","具备所有的权限"),
    WAREHOUSE_KEEPER(2,"仓库管理员","仓库管理员"),
    SALE(3,"销售","销售"),
    FINANCE(4,"财务","财务管理人员");

    RoleEnum(Integer roleId,String roleName, String roleDesc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
    }
    private Integer roleId;
    private String roleName;
    private String roleDesc;

    public static RoleEnum getEnum(Integer roleId) {
        if (roleId == null) {
            return null;
        }
        for (RoleEnum e : values()) {
            if (e.roleId.equals(roleId)) {
                return e;
            }
        }
        return null;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
