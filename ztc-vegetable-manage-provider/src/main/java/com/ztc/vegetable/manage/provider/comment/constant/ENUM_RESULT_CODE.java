package com.ztc.vegetable.manage.provider.comment.constant;

/**
 * 提交状态
 *
 * @author WangTianZhou
 * @Class ENUM_KLG_RESULT
 * @date 2019/10/9
 */
public enum ENUM_RESULT_CODE {
    /**
     * 0-系统级异常
     */
    FAIL("0", "系统异常"),
    /**
     * 1-交易成功
     */
    SUCCESS("1", "交易成功");

    private String code;
    private String desc;

    public String getCode() {
        return code;
    }

    private ENUM_RESULT_CODE(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
