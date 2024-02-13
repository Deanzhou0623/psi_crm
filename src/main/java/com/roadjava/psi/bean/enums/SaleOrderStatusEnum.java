package com.roadjava.psi.bean.enums;/*
 *ClassName:SaleOrderStatusEnum
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 21:36
 */

public enum SaleOrderStatusEnum {
    DONE(0,"已完成"),
    REFUND_ED(1,"已退款");

    SaleOrderStatusEnum(Integer status, String desc) {
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

    public static SaleOrderStatusEnum getEnum(Integer status) {
        if (status == null) {
            return null;
        }
        for (SaleOrderStatusEnum e : values()) {
            if (e.status.equals(status)) {
                return e;
            }
        }
        return null;
    }
}
