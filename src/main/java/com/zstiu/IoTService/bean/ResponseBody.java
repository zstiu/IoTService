package com.zstiu.IoTService.bean;

/**
 * Created by Administrator on 2018/3/15.
 */
public class ResponseBody {
    private int code = 1000;

    private boolean success = false;

    private String message = "";

    private Object data = "";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getdata() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
