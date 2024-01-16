package com.roadjava.psi.interceptor;/*
 *ClassName:AuthorizationInterceptor
 *Description: 权限拦截器
 *@Author:deanzhou
 *@Date:2024-01-15 21:15
 */

import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.vo.UserVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.context.UserContext;
import com.roadjava.psi.utils.JwtUtil;
import com.roadjava.psi.utils.ResponseUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*
         axios对于非简单请求会先发OPTIONS类型的请求用来获取响应信息，如：
            Access-Control-Allow-Headers: authorization
            Access-Control-Allow-Methods: GET
            Access-Control-Allow-Origin: *
            Access-Control-Max-Age: 3600
            Allow: GET, HEAD, POST, PUT, DELETE, OPTIONS, PATCH
          再根据这个响应头处理自己的请求(如不允许跨域则失败,允许则发送)
         */
        String method = request.getMethod();
        if (HttpMethod.OPTIONS.matches(method)) {
            // 去执行跨域filter
            return true;
        }
        String tokenToVerify = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (tokenToVerify == null) {
            // 请求头中没有时从请求参数中获取
            tokenToVerify = request.getParameter(Constants.TOKEN_KEY);
            if (StringUtils.isBlank(tokenToVerify)) {
                ResponseUtil.respAppJson(response, ResultVO.buildFailure("user_token不能为空"));
                return false;
            }
        }
        ResultVO<DecodedJWT> verify = JwtUtil.verify(tokenToVerify);
        if (verify.isFailed()) {
            ResponseUtil.respAppJson(response,verify);
            return false;
        }
        // 校验通过
        UserVO userVO = JwtUtil.parse(verify.getData(), UserVO.class);
        UserContext.set(userVO);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        UserContext.remove();
    }
}
