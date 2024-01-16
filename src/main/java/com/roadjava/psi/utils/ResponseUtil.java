package com.roadjava.psi.utils;/*
 *ClassName:ResponseUtil
 *Description: response class
 *@Author:deanzhou
 *@Date:2024-01-15 21:19
 */

import com.alibaba.fastjson.JSON;
import com.roadjava.psi.bean.constants.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtil {
    private static void write(HttpServletResponse resp, String result) {
        try (PrintWriter writer = resp.getWriter()) {
            writer.print(result);
            writer.flush();
        } catch (IOException e) {
            log.error("写响应异常",e);
        }
    }

    public static void respAppJson(HttpServletResponse resp, Object obj) {
        resp.setCharacterEncoding(Constants.UTF_8.name());
        resp.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        write(resp, JSON.toJSONString(obj));
    }
}
