package com.ztc.vegetable.manage.restful.comment.dto;

import com.ztc.vegetable.manage.restful.comment.constant.ENUM_RESTFUL_COMMON_CODE;

public class ResultModel {
    private int code;
    private String message = "";
    private Object content;

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.content = "";
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(0, "成功", content);
    }

    public static ResultModel ok() {
        return new ResultModel(0, "成功");
    }

    public static ResultModel error(Object[] objects) {
        return null != objects && objects.length == 2 ? new ResultModel(Integer.valueOf(String.valueOf(objects[0])), String.valueOf(objects[1])) : new ResultModel(ENUM_RESTFUL_COMMON_CODE.SYSTEM_ERROR.getCode(), ENUM_RESTFUL_COMMON_CODE.SYSTEM_ERROR.getMessage());
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getContent() {
        return this.content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}

