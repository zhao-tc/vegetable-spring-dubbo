package com.ztc.vegetable.manage.provider.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.goods.service.GoodService;
import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.api.goods.service.res.QryGoodListResModel;
import com.ztc.vegetable.manage.api.init.res.NewDataModel;
import com.ztc.vegetable.manage.api.init.service.NewDataService;
import com.ztc.vegetable.manage.api.order.req.QryAorderReqModel;
import com.ztc.vegetable.manage.api.order.res.QryAorderResModel;
import com.ztc.vegetable.manage.api.order.service.OrderService;
import com.ztc.vegetable.manage.api.user.service.UserService;
import com.ztc.vegetable.manage.db.mapper.UserMapper;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.provider.ProviderApplication;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ProviderApplication.class)
class UserServiceImplTest {
    @Reference
    private UserService  userService;
    @Reference
    private NewDataService newDataService;
    @Reference
    private GoodService goodService;
    @Reference
    private OrderService orderService;
    @Autowired
    UserMapper userMapper;

    @Test
    void showPageUsers() {
//        PageHelper.startPage(0,2);
//        UserPageInfoResModel userPageInfoResModel = userService.showPageUsers();
//        System.out.println(JSON.toJSONString(userPageInfoResModel));
    }
     @Test
    void newDataServiceImpl(){
         NewDataModel newDataModel = newDataService.newDatas();
         System.out.println(JSON.toJSONString(newDataModel));

     }

     @Test
    void  oodService(){
//         qryMainPageModel qryMainPageModel = goodService.qryMainPage();
//         System.out.println(JSON.toJSONString(qryMainPageModel));
     }


    @Test
    void  qryGoodList(){
        QryGoodListReqModel qryGoodListReqModel = new QryGoodListReqModel();
        qryGoodListReqModel.setName("11");
        QryGoodListResModel qryGoodListResModel = goodService.qryGoodList(qryGoodListReqModel);
        System.out.println(JSON.toJSONString(qryGoodListResModel));
    }
    @Test
    void  quser(){
        User user = userMapper.selectByPrimaryKey("90");
        System.out.println(JSON.toJSONString(user));

        User user1 = new User();
        user1.setUserid(90);
        user1.setIsDelete("0");
        user = userMapper.selectByPrimaryKey(user1);
        System.out.println(JSON.toJSONString(user));
        user1.setIsDelete("0");
        user = userMapper.selectOne(user1);
        System.out.println(JSON.toJSONString(user));
    }
@Test
    void orderService(){
    QryAorderReqModel qryAorderReqModel = new QryAorderReqModel();
    QryAorderResModel qryAorderResModel = orderService.qryOrderByAgent(qryAorderReqModel);
}
}
