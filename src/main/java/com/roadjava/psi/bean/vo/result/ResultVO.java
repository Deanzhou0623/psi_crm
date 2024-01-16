package com.roadjava.psi.bean.vo.result;/*
 *ClassName:ResultVO
 *Description: result class to return to the front-end
 *@Author:deanzhou
 *@Date:2024-01-15 20:56
 */

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ResultVO<T> {
    /**
     * 错误标识码
     */
    private String errCode;
    /**
     * 失败时的提示信息
     */
    private String errMsg;
    /**
     * 本次返回结果是否成功
     */
    private Boolean success = Boolean.TRUE;
    private T data;
    private Long total;
    /**
     * 其他额外信息
     */
    private Map<String,Object> attributes = new HashMap<>();

    private ResultVO(){
    }

    public void addAttr(String key,Object value){
        this.attributes.put(key,value);
    }

    public static <T> ResultVO<T> buildEmptySuccess(){
        return new ResultVO<>();
    }

    public static <T> ResultVO<T> buildSuccess(T t){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(t);
        return resultVO;
    }

    public static <T> ResultVO<T> buildSuccess(T t, Long total){
        ResultVO<T> resultVO = buildSuccess(t);
        resultVO.setTotal(total);
        return resultVO;
    }

    public static <T> ResultVO<T> buildFailure(String code, String errMsg) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setErrCode(code);
        resultVO.setErrMsg(errMsg);
        resultVO.setSuccess(false);
        return resultVO;
    }

    public static <T> ResultVO<T> buildFailure(String errMsg) {
        return buildFailure(null,errMsg);
    }

    /**
     * 方便判断是否成功
     */
    public boolean isSuccess() {
        return success;
    }
    /**
     * 方便判断是否成功
     */
    public boolean isFailed() {
        return !isSuccess();
    }
}
