package com.roadjava.psi.handler;/*
 *ClassName:GoodsHandler
 *Description: goods controller
 *@Author:deanzhou
 *@Date:2024-01-21 22:17
 */

import com.roadjava.psi.bean.request.user.LoginReq;
import com.roadjava.psi.bean.request.user.UpdatePwdReq;
import com.roadjava.psi.bean.request.user.UserAddReq;
import com.roadjava.psi.bean.request.user.UserSearchReq;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.context.UserContext;
import com.roadjava.psi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserHandler {

    @Resource
    private UserService userService;


    /*
    * 登入
    *  @return 用户token
    * */
    @PostMapping("/login")
    public ResultVO<String> login(@RequestBody @Validated LoginReq req){
        return userService.login(req);
    }

    /*
    * 通过token获取用户信息
    * */
    @GetMapping ("/getUserByToken")
    public ResultVO<UserVO> getUserByToken() {
        return ResultVO.buildSuccess(UserContext.get());
    }

    /*
    * update pwd
    * */
    @PostMapping("/updatePwd")
    public ResultVO<String> updatePwd(@RequestBody @Validated UpdatePwdReq req){
        userService.updatePwd(req);
        return ResultVO.buildSuccess("密码修改成功");
    }
    /*
    * add
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated UserAddReq req){
        userService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }
     /*
     * @GetMapping 因为需要get id - deleteById
     * */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long userId) {
        userService.deleteById(userId);
        return ResultVO.buildSuccess("删除成功");
    }
    /*
    * 查询用户列表
    * */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<UserVO>> loadTable(@RequestBody @Validated UserSearchReq req){
         return userService.loadTable(req);
    }
    /*
    * select user by id
    * */
    @PostMapping("/getUserById")
    public ResultVO<UserVO> getUserById(@RequestBody UserSearchReq req){
        Long userId = req.getUserId();
        //1. 是否存在
        if (userId ==null){
            return ResultVO.buildFailure("用户名不能为空");
        }
        //2. if not null
        return userService.getUserById(req);
    }
    
    
}
