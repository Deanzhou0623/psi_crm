package com.roadjava.psi.service.impl;/*
 *ClassName:UserServiceImpl
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-22 22:17
 */

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.roadjava.psi.bean.entity.UserDO;
import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.UserConvert;
import com.roadjava.psi.ex.BizException;
import com.roadjava.psi.mapper.UserMapper;
import com.roadjava.psi.service.UserService;
import com.roadjava.psi.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserConvert userConvert;
    /*
    * 用户登入
    * */
    @Override
    public ResultVO<String> login(LoginReq req) {
        //1. 搜索用户名和密码
        LambdaQueryWrapper<UserDO> query = new LambdaQueryWrapper<>();
        query.eq(UserDO::getUserName,req.getUserName())
                .eq(UserDO::getPwd,req.getPwd());
        //2. 是否存在
        UserDO userDO = userMapper.selectOne(query);
        if (userDO ==null){
            return ResultVO.buildFailure("用户名或密码不正确");
        }
        //3.用去掉密码后的userVO 生成token并返回
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userDO,userVO);
        userVO.setRoleName(RoleEnum.getEnum(userVO.getRoleId()).getRoleName());
        String token = JwtUtil.getToken(userVO);
        return ResultVO.buildSuccess(token);
    }

    /*
    * 修改密码
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updatePwd(UpdatePwdReq req) {
        //1. 找到该用户
        UserDO userDO = userMapper.selectById(req.getId());
        //2. 用户是否存在
        if (userDO ==null){
            throw new BizException("用户不存在");
        }
        //3. 原始密码不正确
        if (!req.getOldPwd().equals(userDO.getPwd())){
            throw new BizException("原始密码不正确");
        }
        //4. 转化对象
        UserDO userToUpdate = userConvert.updatePwdReq2DO(req);
        //5. userMapper
        userMapper.updateById(userToUpdate);
    }
    /*
    * select user by id
    * */
    @Override
    public ResultVO<UserVO> getUserById(UserSearchReq req) {
        UserDO userDO = userMapper.selectById(req.getUserId());
        UserVO userVO = userConvert.entity2vo(userDO);
        return ResultVO.buildSuccess(userVO);
    }

    /*
    * add user
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(UserAddReq req) {
        //1. 转化对象
        UserDO userDO = userConvert.addReq2DO(req);
        userMapper.insert(userDO);
    }

    /*
    * delete user by id 
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long userId) {
        userMapper.deleteById(userId);
    }

    /*
    * return user loadTable
    * */
    @Override
    public ResultVO<TableResult<UserVO>> loadTable(UserSearchReq req) {
        return null;
    }
}