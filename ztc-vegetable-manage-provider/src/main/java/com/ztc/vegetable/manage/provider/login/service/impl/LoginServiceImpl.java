package com.ztc.vegetable.manage.provider.login.service.impl;

import com.ztc.vegetable.manage.api.login.req.LoginInfoReqModel;
import com.ztc.vegetable.manage.api.login.res.LoginInfoResModel;
import com.ztc.vegetable.manage.api.login.res.RegisterResModel;
import com.ztc.vegetable.manage.api.login.service.LoginService;
import com.ztc.vegetable.manage.db.mapper.AdressMapper;
import com.ztc.vegetable.manage.db.mapper.UserMapper;
import com.ztc.vegetable.manage.db.model.Adress;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.BaseCheckException;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import com.ztc.vegetable.manage.provider.comment.service.TokenService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    public final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AdressMapper adressMapper;
    @Autowired
    private TokenService tokenService;
    @Override
    public LoginInfoResModel checkUserInfo(LoginInfoReqModel loginInfoReqModel) {
        LoginInfoResModel loginInfoResModel = new LoginInfoResModel();
        try {
            User user = new User();
            BaseCheckException.checkNotEmpty(loginInfoReqModel.getName(),"用户名为空");
            BaseCheckException.checkNotEmpty(loginInfoReqModel.getPasswd(),"密码为空");
            user.setUsername(loginInfoReqModel.getName());
            user.setPassword(loginInfoReqModel.getPasswd());
            user.setIsDelete("0");
            List<User> select = userMapper.select(user);
            BaseCheckException.isTrue(null == select || select.size() != 0,"请输入正确的账号和密码");
            User user1 = select.get(0);
            String token = tokenService.getToken(user1);
            loginInfoResModel.setName(user1.getUsername());
            loginInfoResModel.setToken(token);
            loginInfoResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            loginInfoResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            loginInfoResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            loginInfoResModel.setResultMessage(te.getMessage());
            logger.error("登录失败", te);
        }
        return loginInfoResModel;
    }

    @Override
    public User qryUserById(Integer Uid) {
        User user = new User();
        user.setUserid(Uid);
        user.setIsDelete("0");
        return userMapper.selectOne(user);
    }

    @Override

    public RegisterResModel register(User user) {
        RegisterResModel registerResModel = new RegisterResModel();
        try {
            User user3 = new User();
            user3.setUsername(user.getUsername());
            User user1 = userMapper.selectOne(user3);
            BaseCheckException.checkNull(user1,"该用户已经存在");
            userMapper.insertSelective(user);
            Adress adress = new Adress();
            adress.setUserid(user.getUserid().toString());
            adress.setAdress(user.getAdress());
            adress.setName(user.getUsername());
            adress.setNum(user.getPhone());
            adress.setStatus("HOT");
            adressMapper.insert(adress);
            registerResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            registerResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            registerResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            registerResModel.setResultMessage(te.getMessage());
            logger.error("注册失败", te);
        }
        return registerResModel;
    }
}
