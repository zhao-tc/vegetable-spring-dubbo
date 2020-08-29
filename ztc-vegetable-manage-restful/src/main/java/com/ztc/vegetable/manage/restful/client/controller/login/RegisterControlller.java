package com.ztc.vegetable.manage.restful.client.controller.login;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.login.res.RegisterResModel;
import com.ztc.vegetable.manage.api.login.service.LoginService;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class RegisterControlller extends BaseRestfulController {
    @Reference
    private LoginService loginService;

    @PostMapping("/register")
    public ResponseEntity<ResultModel> register(@RequestBody User user
    ) {
        try {
            logger.info("调用用户注册接口请求报文：" + JSON.toJSONString(user));
            RegisterResModel register = loginService.register(user);
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(register.getResultCode())) {
                return this.resultResponseSuccess(register);
            } else {
                return this.resultResponseFault(new ContentModel(register.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用用户注册查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
}
