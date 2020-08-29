package com.ztc.vegetable.manage.api.user.service.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.db.model.User;

import java.io.Serializable;

public class QryUserInfoResModel extends ContentModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
