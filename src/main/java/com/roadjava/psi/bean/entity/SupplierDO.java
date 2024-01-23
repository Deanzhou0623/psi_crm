package com.roadjava.psi.bean.entity;/*
 *ClassName:SupplierDO
 *Description: 供应商对象
 *@Author:deanzhou
 *@Date:2024-01-21 22:11
 */

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("supplier")
public class SupplierDO {
    /**
     * 系统编号
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 供应商名
     */
    private String name;
}
