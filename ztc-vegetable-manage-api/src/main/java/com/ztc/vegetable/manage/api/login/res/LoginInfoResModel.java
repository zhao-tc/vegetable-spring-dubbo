package com.ztc.vegetable.manage.api.login.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;

import java.io.Serializable;

public class LoginInfoResModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private  String name;

    /**
     * token
     * @return
     */
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
