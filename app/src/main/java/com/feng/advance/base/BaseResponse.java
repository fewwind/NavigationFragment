package com.feng.advance.base;

public class BaseResponse<T> {
    public int errno;
    public String errmsg;
    public T data;

    @Override
    public String toString() {
        return "BaseResponse{" +
                "errno=" + errno +
                ", errmsg='" + errmsg + '\'' +
                ", data=" + data +
                '}';
    }
}
