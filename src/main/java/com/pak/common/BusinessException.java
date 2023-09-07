package com.pak.common;

/**
 * Created by huyiwei on 2018/4/21.
 */
public class BusinessException extends Exception{
    private ErrorCode errorCode;
    private HttpCode httpCode;

    public BusinessException(ErrorCode errorCode,HttpCode httpCode,String message){
        super(message);
        this.errorCode = errorCode;
        this.httpCode = httpCode;
    }
}
