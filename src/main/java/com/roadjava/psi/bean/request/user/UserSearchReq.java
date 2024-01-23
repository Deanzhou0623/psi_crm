package com.roadjava.psi.bean.request.user;/*
 *ClassName:UserSearchReq
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-22 23:38
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.roadjava.psi.bean.request.BaseSearchReq;
import lombok.Data;

@Data
public class UserSearchReq extends BaseSearchReq {
    private String userName;
    private Long userId;
}
