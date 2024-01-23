package com.roadjava.psi.convert;/*
 *ClassName:UserConvert
 *Description: user request convert to object
 *@Author:deanzhou
 *@Date:2024-01-22 22:22
 */

import com.roadjava.psi.bean.entity.UserDO;
import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class UserConvert {
    public UserDO addReq2DO(UserAddReq req) {
        UserDO entity = new UserDO();
        BeanUtils.copyProperties(req,entity);
        return entity;
    }
    public UserDO updatePwdReq2DO(UpdatePwdReq req) {
        UserDO entity = new UserDO();
        entity.setId(req.getId());
        entity.setPwd(req.getNewPwd());
        return entity;
    }

    public UserVO entity2vo(UserDO userDO) {
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO, userVO);
        // 设置角色名
        Optional.ofNullable(RoleEnum.getEnum(userVO.getRoleId()))
                .ifPresent(roleEnum -> userVO.setRoleName(roleEnum.getRoleName()));
        return userVO;
    }
}
