package com.roadjava.psi.handler;/*
 *ClassName:RoleHandler
 *Description: 权限控制 only admin can search
 *@Author:deanzhou
 *@Date:2024-01-21 22:19
 */

import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.vo.RoleVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
public class RoleHandler {
    /**
     * 查询角色列表
     */
    @GetMapping("/listRoles")
    public ResultVO<List<RoleVO>> loadTable() {
        List<RoleVO> list = new ArrayList<>();
        for (RoleEnum value : RoleEnum.values()) {
            if (value.equals(RoleEnum.ADMIN)) {
                // 超级管理员不能新增
                continue;
            }
            RoleVO roleVO = new RoleVO().setRoleId(value.getRoleId()).setRoleName(value.getRoleName());
            list.add(roleVO);
        }
        return ResultVO.buildSuccess(list);
    }
}
