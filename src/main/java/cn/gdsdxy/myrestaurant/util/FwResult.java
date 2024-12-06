package cn.gdsdxy.myrestaurant.util;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FwResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int status;

    private String message;

    boolean success;
    @JsonFormat(pattern = "yyyy-mm-dd hh:mm:ss",timezone = "GMT+8")
    Date time;

    private T data;

    public static <T> FwResult<T> ok() {
        return restResult(null, FwConstants.SUCCESS, true,FwConstants.MSG_SUCCESS);
    }
    public static <T> FwResult<T> okMsg(String msg) {
        return restResult(null, FwConstants.SUCCESS, true,msg);
    }
    public static <T> FwResult<T> ok(T data) {
        return restResult(data, FwConstants.SUCCESS, true,FwConstants.MSG_SUCCESS);
    }

    public static <T> FwResult<T> ok(T data, String msg) {
        return restResult(data, FwConstants.SUCCESS, true,msg);
    }
    public static <T> FwResult<T> okMeta(T data) {
        return restResult(data, FwConstants.SUCCESS, true,null);
    }
    public static <T> FwResult<T> ok(T data, String msg, Object meta) {
        return restResult(data, FwConstants.SUCCESS, true,msg);
    }

    public static <T> FwResult<T> failed() {
        return restResult(null, FwConstants.FAIL,false, FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failedMsg(String msg) {
        return restResult(null, FwConstants.FAIL, false,msg);
    }

    public static <T> FwResult<T> failedMsg(int code,String msg) {
        return restResult(null, code, false,msg);
    }

    public static <T> FwResult<T> tokenFailedMsg(String msg) {
        return restResult(null, FwConstants.RELOGIN, false,msg);
    }

    public static <T> FwResult<T> urlNotFoundMsg(String msg) {
        return restResult(null, FwConstants.NOTFOUND, false,msg);
    }

    public static <T> FwResult<T> failed(T data) {
        return restResult(data, FwConstants.FAIL, false,FwConstants.MSG_FAIL);
    }

    public static <T> FwResult<T> failed(T data, String msg) {
        return restResult(data, FwConstants.FAIL, false,msg);
    }

    private static <T> FwResult<T> restResult(T data, int code, boolean success,String msg) {
        FwResult fwResult = new FwResult();
        fwResult.setStatus(code);
        fwResult.setSuccess(success);
        fwResult.setData(data);
        fwResult.setMessage(msg);
        fwResult.setTime(new Date());
        return fwResult;
    }


}
