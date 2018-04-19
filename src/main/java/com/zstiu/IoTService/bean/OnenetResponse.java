package com.zstiu.IoTService.bean;

public class OnenetResponse {
    private int errno = 1000;

    private String error = "";

    private Object data = "";

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getError() {
        return error;
    }

    public void setError(String message) {
        this.error = message;
    }

    public Object getdata() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
