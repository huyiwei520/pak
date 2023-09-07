package com.pak.common;

/**
 * Created by huyiwei on 2018/4/21.
 */
public class ResultModel {

    private Integer status;
    private Integer code;
    private String info;
    private Object data;
    private Page page;

    public ResultModel(){
        this.status = ErrorCode.sucess.value();
        this.code = HttpCode.sucess.value();
        this.info = null;
        this.data = null;
        this.page = null;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }
}
