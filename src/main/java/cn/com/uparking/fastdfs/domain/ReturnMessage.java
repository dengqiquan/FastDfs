package cn.com.uparking.fastdfs.domain;

import java.util.Map;

/**
 * Created by QinMing on 2017/07/01.
 */
public class ReturnMessage {
    private String code;
    private String message;
    private Map<String, Object> context;

    public ReturnMessage() {}

    public ReturnMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ReturnMessage(String code, String message, Map<String, Object> context) {
        this.code = code;
        this.message = message;
        this.context = context;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getContext() {
        return context;
    }

    public void setContext(Map<String, Object> context) {
        this.context = context;
    }
}
