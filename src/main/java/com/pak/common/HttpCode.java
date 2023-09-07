package com.pak.common;

/**
 * Created by huyiwei on 2018/4/21.
 */
public enum  HttpCode {
    sucess(200),
    errparam(400),
    unauthorized(401),
    forbidden(403),
    notFound(404),
    internalServerError(500),
    serviceUnavailable(503);

    private int code;

    HttpCode(int code){
        this.code = code;
    }

    public int value(){
        return this.code;
    }

    public static HttpCode valueOf(int code){
        switch (code){
            case 200:
                return HttpCode.sucess;
            case 400:
                return HttpCode.errparam;
            case 401:
                return HttpCode.unauthorized;
            case 403:
                return HttpCode.forbidden;
            case 404:
                return HttpCode.notFound;
            case 500:
                return HttpCode.internalServerError;
            case 503:
                return HttpCode.serviceUnavailable;
            default:
                return HttpCode.internalServerError;
        }
    }
}
