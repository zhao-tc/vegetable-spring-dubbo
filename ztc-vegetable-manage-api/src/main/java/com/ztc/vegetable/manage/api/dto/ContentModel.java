package com.ztc.vegetable.manage.api.dto;

/**
 * @author zhaotiancheng
 * @Class ContentModel
 * @date 2020-01-2020/1/2 13:45
 */
public class ContentModel {
    private static final long serialVersionUID = 1L;
    private String resultCode = "1";
    private String resultMessage = "";

    public ContentModel() {
    }

    public ContentModel(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public ContentModel(String result, String resultMessage) {
        this.resultCode = result;
        this.resultMessage = resultMessage;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return this.resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }
}
