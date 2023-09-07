package com.pak.common;

/**
 * Created by huyiwei on 2018/4/21.
 */
public class BaseException extends Exception{
    public static final int UNKOWN = -1;//未知错误
    public static final int OK = 200;
    public static final int NOT_CONTENT =204;
    public static final int LOGIN_STATUS =401;

    public Integer getCode(){
        return code;
    }

    protected Integer code;

    protected Integer status;

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BaseException(Integer statu,Integer code,String message){
        super(message);
        this.status = status;
        this.code = code;
    }
}
