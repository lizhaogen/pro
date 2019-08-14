package com.qf.v2.common.vo;


import java.io.Serializable;
import java.util.Map;

public class ResultVO<T> implements Serializable {
    private static ResultVO resultVO=new ResultVO();
    private Boolean result;
    private String message;
    private T data;


    public ResultVO() {
    }

    public ResultVO(Boolean result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public static ResultVO errorResult(){
        resultVO.setResult(false);
        resultVO.setMessage("error");
        return resultVO;
    }

    public static ResultVO successResult(){
        resultVO.setResult(true);
        resultVO.setMessage("success");
        return resultVO;
    }

    public static ResultVO successResult(String message){
        resultVO.setResult(true);
        resultVO.setMessage(message);
        return resultVO;
    }

    public static ResultVO successResult(Map<String,Object> data){
        resultVO.setResult(true);
        resultVO.setMessage("success");
        resultVO.setData(data);
        return resultVO;
    }
    public static ResultVO successResult(String message,Map<String,Object> data){
        resultVO.setResult(true);
        resultVO.setMessage(message);
        resultVO.setData(data);
        return resultVO;
    }






    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
