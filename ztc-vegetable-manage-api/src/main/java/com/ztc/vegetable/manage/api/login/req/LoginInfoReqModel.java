package com.ztc.vegetable.manage.api.login.req;

import java.io.Serializable;

public class LoginInfoReqModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 用户名
     */
    private String name;
    /**
     * 用户密码
     */
    private String passwd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
