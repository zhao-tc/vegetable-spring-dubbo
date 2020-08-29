package com.ztc.vegetable.manage.restful.client.controller.login;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.login.req.LoginInfoReqModel;
import com.ztc.vegetable.manage.api.login.res.LoginInfoResModel;
import com.ztc.vegetable.manage.api.login.service.LoginService;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class LoginController extends BaseRestfulController {
    @Reference
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<ResultModel> login(@RequestBody LoginInfoReqModel loginInfoReqModel
                                                    ) {
        try {
            logger.info("调用用户登录接口请求报文：" + JSON.toJSONString(loginInfoReqModel));
            LoginInfoResModel loginInfoResModel = loginService.checkUserInfo(loginInfoReqModel);
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(loginInfoResModel.getResultCode())) {
                return this.resultResponseSuccess(loginInfoResModel);
            } else {
                return this.resultResponseFault(new ContentModel(loginInfoResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用商品分页查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

}
