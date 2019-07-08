package com.example.download_data_2.utill;

import java.io.Serializable;

public class ResultBean<T> implements Serializable {
    private Integer code;
    private String mesg;
    private T data;

    public ResultBean(Integer code, String mesg, T data) {
        this.code = code;
        this.mesg = mesg;
        this.data = data;
    }

    public ResultBean() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMesg() {
        return mesg;
    }

    public void setMesg(String mesg) {
        this.mesg = mesg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "code=" + code +
                ", mesg='" + mesg + '\'' +
                ", data=" + data +
                '}';
    }
}
