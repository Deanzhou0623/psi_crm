package com.roadjava.psi.bean.enums;/*
 *ClassName:RetStatusEnum
 *Description: 审核状态
 *@Author:deanzhou
 *@Date:2024-01-28 15:56
 */

public enum RetStatusEnum {
    TO_AUDIT(0,"待审核"),
    AGREED(1,"审核通过"),
    REJECTED(2,"审核拒绝");
    RetStatusEnum(Integer status,String desc) {
        this.status = status;
        this.desc = desc;
    }
    private Integer status;
    private String desc;

    public Integer getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }

    public static RetStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (RetStatusEnum e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
