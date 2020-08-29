package com.ztc.vegetable.manage.provider.user.dao;

import com.ztc.vegetable.manage.api.user.service.req.QryAUserReqModel;
import com.ztc.vegetable.manage.db.model.User;

import java.util.List;

public interface ShowPageUsersDAO {
    /**
     * 条件查询用户
     */
   List<User> showPageUsers(QryAUserReqModel qryAUserReqModel);
}
