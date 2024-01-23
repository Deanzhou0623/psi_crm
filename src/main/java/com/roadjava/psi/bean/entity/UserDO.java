package com.roadjava.psi.bean.entity;/*
 *ClassName:UserDO
 *Description: 员工对象
 *@Author:deanzhou
 *@Date:2024-01-21 22:10
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user")
public class UserDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String pwd;
    /**
     * 角色id
     * @see com.roadjava.psi.bean.enums.RoleEnum
     */
    private Integer roleId;
}
