package com.roadjava.psi.interceptor;/*
 *ClassName:ExceptionAdvice
 *Description: handle exception
 *@Author:deanzhou
 *@Date:2024-01-15 21:28
 */

import com.alibaba.fastjson.JSON;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.ex.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.validation.BindException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
    /**
     * 校验异常拦截
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public ResultVO<String> handleEx(BindException e) {
        log.error("校验出错:",e);
        List<FieldError> fieldErrors = e.getFieldErrors();
        return spliceValidationResult(fieldErrors);
    }

    private ResultVO<String> spliceValidationResult(List<FieldError> fieldErrors) {
        List<String> list = new ArrayList<>();
        for (FieldError fe : fieldErrors) {
            StringBuilder sb = new StringBuilder();
            sb.append("属性:").append(fe.getField())
                    .append(",属性的值:").append(fe.getRejectedValue())
                    .append(",校验不通过，原因:").append(fe.getDefaultMessage());
            list.add(sb.toString());

        }
        return ResultVO.buildFailure(JSON.toJSONString(list));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO<String> handleEx(MethodArgumentNotValidException e) {
        log.error("方法入参异常:",e);
        BindingResult bindingResult = e.getBindingResult();
        // 获取没通过校验的字段详情
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return spliceValidationResult(fieldErrors);
    }

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public ResultVO<String> handleBizEx(Exception e) {
        log.error("业务异常处理:",e);
        return ResultVO.buildFailure(e.getMessage());
    }

    /**
     * 处理未精准匹配的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO<String> handleEx(Exception e) {
        log.error("统一异常处理:",e);
        return ResultVO.buildFailure("系统异常");
    }
}
