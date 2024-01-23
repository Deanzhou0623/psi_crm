package com.roadjava.psi.service;/*
 *ClassName:UserService
 *Description: user  接口类
 *@Author:deanzhou
 *@Date:2024-01-22 22:10
 */

import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

import javax.annotation.Resource;

public interface UserService {
    /*
    * 登入
    * @return 用户token
    * */
    ResultVO<String> login(LoginReq req);

    /*
    * 修改密码
    * */
    void updatePwd(UpdatePwdReq req);

    /*
    * 根据id查找user
    * */
    ResultVO<UserVO> getUserById(UserSearchReq req);
    /*
    * 添加user
    * */
    void add(UserAddReq req);
    /*
    * 根据ID删除user
    * */
    void deleteById(Long userId);

    /*
    * 查询用户列表
    * */
    ResultVO<TableResult<UserVO>> loadTable(UserSearchReq req);
}
