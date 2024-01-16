package com.roadjava.psi.context;/*
 *ClassName:UserContext
 *Description: 多线程
 *@Author:deanzhou
 *@Date:2024-01-15 21:26
 */

import com.roadjava.psi.bean.vo.UserVO;

public class UserContext {
    private static  ThreadLocal<UserVO> threadLocal=new ThreadLocal<>();

    public static void set(UserVO user){
        threadLocal.set(user);
    }
    public static  UserVO get(){
        return threadLocal.get();
    }
    public static void remove(){
        threadLocal.remove();
    }
}
