package com.ztc.vegetable.manage.restful.comment.constant;

public enum ENUM_RESTFUL_COMMON_CODE {
    SYSTEM_ERROR(40001, "服务运行异常"),
    NOT_AUTHORIZATION(40002, "接口没有授权"),
    HTTP_MESSAGE_NOT_READABLE(40003, "请求参数解析异常"),
    NOT_METHOD(40004, "接口不存在");

    private int code;
    private String message;

    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }

    private ENUM_RESTFUL_COMMON_CODE(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByCode(String code) {
        ENUM_RESTFUL_COMMON_CODE[] var1 = values();
        int var2 = var1.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            ENUM_RESTFUL_COMMON_CODE obj = var1[var3];
            if (code.equals(obj.getCode())) {
                return obj.getMessage();
            }
        }

        return null;
    }

    public String toJsonString() {
        return "{\"code\":" + this.code + ", \"message\":\"" + this.message + "\"}";
    }

    public Object[] toErrorModel(String... msg) {
        return null != msg && msg.length > 0 && null != msg[0] && !"".equals(msg[0].trim()) ? new Object[]{this.code, this.message + "," + msg[0]} : new Object[]{this.code, this.message};
    }
}
