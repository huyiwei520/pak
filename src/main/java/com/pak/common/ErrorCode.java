package com.pak.common;

/**
 * Created by huyiwei on 2018/4/21.
 */
public enum ErrorCode {
    unkown(-1),
    sucess(0),
    nologined(1),
    errtoken(2);

    private int code;

    ErrorCode(int code){
        this.code = code;
    }

    public int value(){
        return this.code;
    }

    public static ErrorCode valueOf(int code){
        switch (code){
            case 0:
                return ErrorCode.sucess;
            case 1:
                return ErrorCode.nologined;
            case 2:
                return ErrorCode.errtoken;
            default:
                return ErrorCode.unkown;
        }
    }
}
