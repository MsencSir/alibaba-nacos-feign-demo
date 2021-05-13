package net.chain.test.dto;

import java.io.Serializable;

/**
 * @Author MsencSir
 * @Date 2020/5/22 15:22
 */

public class Result<T> implements Serializable {
    private boolean success;
    private int code;
    private String remark;
    private T data;

    public Result(boolean success, int code, String remark, T data) {
        this.success = success;
        this.code = code;
        this.remark = remark;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    public static <T> Result<T> succ(T date){
        return new Result<T>(true,0,"成功",date);
    }
    public static <T> Result<T> fail(String remark){
        return new Result<T>(false,0,remark,null);
    }
}
