package com.ztc.vegetable.manage.api.user.service;

import com.ztc.vegetable.manage.api.user.service.req.QryAUserReqModel;
import com.ztc.vegetable.manage.api.user.service.res.DeleteUserModel;
import com.ztc.vegetable.manage.api.user.service.res.ModifyUserModel;
import com.ztc.vegetable.manage.api.user.service.res.QryUserInfoResModel;
import com.ztc.vegetable.manage.api.user.service.res.UserPageInfoResModel;
import com.ztc.vegetable.manage.db.model.User;

public interface UserService {
    /**
     * 分页查询
     * @return
     */
    UserPageInfoResModel showPageUsers(QryAUserReqModel qryAUserReqModel);
    /**
     * 删除
     */
    DeleteUserModel deleteUser(String id);
    /**
     * 修改用户信息
     */
    ModifyUserModel modifyUser(User user);
    /**
     * 查询用户信息
     */
    QryUserInfoResModel qryUserInfo(User user);
}
