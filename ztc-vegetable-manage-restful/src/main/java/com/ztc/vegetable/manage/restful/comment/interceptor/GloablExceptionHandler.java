package com.ztc.vegetable.manage.restful.comment.interceptor;

import com.ztc.vegetable.manage.restful.comment.model.TokenResModel;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jinbin
 * @date 2018-07-08 22:37
 */
@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Object handleException(Exception e) {
        String msg = e.getMessage();
        if (msg == null || msg.equals("")) {
            msg = "服务器出错";
        }
        TokenResModel tokenResModel = new TokenResModel();
        tokenResModel.setResultCode(msg);
        tokenResModel.setResultMessage("请重新登录");
        return tokenResModel;
    }
}
