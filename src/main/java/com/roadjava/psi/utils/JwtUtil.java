package com.roadjava.psi.utils;/*
 *ClassName:JwtUtils
 *Description: jwt class 
 *@Author:deanzhou
 *@Date:2024-01-15 20:52
 */

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.InvalidClaimException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.roadjava.psi.bean.vo.result.ResultVO;
import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;

@Slf4j
public class JwtUtil {
    public static final String SECRET = "123456";
    /**
     * 生成token
     */
    public static <T> String getToken(T t) {
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.YEAR, 1);
        JWTCreator.Builder builder = JWT.create()
                // 声明
                .withClaim(t.getClass().getSimpleName(), JSON.toJSONString(t));
        String token = builder.withExpiresAt(instance.getTime())
                .sign(Algorithm.HMAC256(SECRET));
        return token;
    }

    /**
     * 验证token合法性
     * @param token ： 前端传来的token
     * @return 验证结果
     */
    public static ResultVO<DecodedJWT> verify(String token) {
        String errMsg;
        try {
            DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(SECRET))
                    .build().verify(token);
            return ResultVO.buildSuccess(decodedJWT);
        } catch (SignatureVerificationException e) {
            errMsg = "签名不一致";
            log.error(errMsg, e);
        } catch (TokenExpiredException e) {
            errMsg = "令牌过期";
            log.error(errMsg, e);
        } catch (AlgorithmMismatchException e) {
            errMsg = "算法不匹配";
            log.error(errMsg, e);
        } catch (InvalidClaimException e) {
            errMsg = "失效的payload";
            log.error(errMsg, e);
        } catch (Exception e) {
            errMsg = "无效的令牌";
            log.error(errMsg, e);
        }
        return ResultVO.buildFailure(errMsg);
    }

    public static <T> T parse(DecodedJWT decodedJWT,Class<T> clz) {
        Claim claim = decodedJWT.getClaim(clz.getSimpleName());
        if (claim == null) {
            return null;
        }
        return JSON.parseObject(claim.asString(), clz);
    }
}
