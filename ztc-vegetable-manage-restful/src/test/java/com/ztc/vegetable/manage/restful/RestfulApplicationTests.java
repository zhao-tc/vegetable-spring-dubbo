package com.ztc.vegetable.manage.restful;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.ztc.vegetable.manage.api.user.service.UserService;
import com.ztc.vegetable.manage.api.user.service.res.UserPageInfoResModel;
import com.ztc.vegetable.manage.db.model.User;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class RestfulApplicationTests {
    @Reference
    private UserService userService;
    @Test
    void contextLoads() {
    }

}
