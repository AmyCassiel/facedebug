package com.jiachang.facedebug.bean;

/**
 * @author Mickey.Ma
 * @date 2020-01-13
 * @description
 */
public class sendFileBean {
    /*
    * "data": "123",//照片 id，每张注册照都有一个 id，限制 32 位。
    * "result": 1,//接口调通
    * "success": true//照片注册成功
    */

    private String data;
    private int result;
    private boolean success;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
