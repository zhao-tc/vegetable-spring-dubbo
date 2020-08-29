package com.ztc.vegetable.manage.api.login.service;

import com.ztc.vegetable.manage.api.login.req.LoginInfoReqModel;
import com.ztc.vegetable.manage.api.login.res.LoginInfoResModel;
import com.ztc.vegetable.manage.api.login.res.RegisterResModel;
import com.ztc.vegetable.manage.db.model.User;

public interface LoginService {
    /**
     * 验证用户信息
     */
    LoginInfoResModel checkUserInfo(LoginInfoReqModel loginInfoReqModel);
    /**
     * 更具用户名查找用户
     */
    User qryUserById(Integer Uid);
    /**
     * 增加账号
     */
    RegisterResModel register(User user);
}
