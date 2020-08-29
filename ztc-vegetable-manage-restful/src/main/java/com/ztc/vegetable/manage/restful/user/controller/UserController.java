package com.ztc.vegetable.manage.restful.user.controller;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.user.service.UserService;
import com.ztc.vegetable.manage.api.user.service.req.QryAUserReqModel;
import com.ztc.vegetable.manage.api.user.service.res.DeleteUserModel;
import com.ztc.vegetable.manage.api.user.service.res.ModifyUserModel;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.user.service.res.UserPageInfoResModel;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhaotiancheng
 * @Class UserController
 * @date 2020-01-2020/1/2 12:23
 */
@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController extends BaseRestfulController {

    @Reference
    private UserService userService;

    @PostMapping("/showAllUsers")
    public ResponseEntity<ResultModel> showAllUsers(@RequestBody QryAUserReqModel qryAUserReqModel) {
        try {
            logger.info("调用showAllUsers展示用户接口请求报文："+qryAUserReqModel);
            UserPageInfoResModel userPageInfoResModel = userService.showPageUsers(qryAUserReqModel);
            userPageInfoResModel.setResultCode("0");
            userPageInfoResModel.setResultMessage("调用成功");
            logger.info("调用showAllUsers展示用户接口返回报文：" + JSON.toJSONString(userPageInfoResModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(userPageInfoResModel.getResultCode())) {
                return this.resultResponseSuccess(userPageInfoResModel);
            } else {
                return this.resultResponseFault(new ContentModel(userPageInfoResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用showAllUsers展示用户接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
    @PostMapping("/modifyUser")
    public ResponseEntity<ResultModel> modifyUser(@RequestBody User user) {
        try {
            logger.info("调用modify修改用户接口请求报文："+JSON.toJSONString(user));
            ModifyUserModel modifyUserModel = userService.modifyUser(user);
            modifyUserModel.setResultCode("0");
            modifyUserModel.setResultMessage("调用成功");
            logger.info("调用modify修改用户接口返回报文：" + JSON.toJSONString(modifyUserModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(modifyUserModel.getResultCode())) {
                return this.resultResponseSuccess(modifyUserModel);
            } else {
                return this.resultResponseFault(new ContentModel(modifyUserModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用modify修改用户接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @GetMapping("/deleteUser")
    public ResponseEntity<ResultModel> deleteUser(String userId) {
        try {
            logger.info("调用deleteUser接口请求报文："+userId);
            DeleteUserModel deleteUserModel = userService.deleteUser(userId);
            deleteUserModel.setResultCode("0");
            deleteUserModel.setResultMessage("调用成功");
            logger.info("调用deleteUser接口返回报文：" + JSON.toJSONString(deleteUserModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(deleteUserModel.getResultCode())) {
                return this.resultResponseSuccess(deleteUserModel);
            } else {
                return this.resultResponseFault(new ContentModel(deleteUserModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("deleteUser-restful接口请求异常:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
}
