package com.ztc.vegetable.manage.provider.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.user.service.UserService;
import com.ztc.vegetable.manage.api.user.service.req.QryAUserReqModel;
import com.ztc.vegetable.manage.api.user.service.res.DeleteUserModel;
import com.ztc.vegetable.manage.api.user.service.res.ModifyUserModel;
import com.ztc.vegetable.manage.api.user.service.res.QryUserInfoResModel;
import com.ztc.vegetable.manage.api.user.service.res.UserPageInfoResModel;
import com.ztc.vegetable.manage.db.mapper.UserMapper;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import com.ztc.vegetable.manage.provider.user.dao.ShowPageUsersDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author zhaotiancheng
 * @Class UserServiceImpl
 * @date 2020-01-2020/1/1 14:08
 */
@Service
public class UserServiceImpl implements UserService {
    public final Log logger =  LogFactory.getLog(this.getClass());
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ShowPageUsersDAO showPageUsersDAO;
    @Override
    public UserPageInfoResModel showPageUsers(QryAUserReqModel qryAUserReqModel) {
        logger.info("正在调用showPageUsers 方法");
        int pageSize = (0 == qryAUserReqModel.getPageSize() ? 10 : qryAUserReqModel.getPageSize());
        int pageNum = (0 == qryAUserReqModel.getPageNum() ? 1 : qryAUserReqModel.getPageNum());
        PageHelper.startPage(pageNum, pageSize);
        List<User> users = showPageUsersDAO.showPageUsers(qryAUserReqModel);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        UserPageInfoResModel userPageInfoResModel = new UserPageInfoResModel();
        userPageInfoResModel.setPageInfo(userPageInfo);
        logger.info("调用showPageUsers 方法结束");
        return userPageInfoResModel;
    }

    @Override
    public DeleteUserModel deleteUser(String id) {
        logger.info("正在调用deleteUser 方法");
        User user = new User();
        user.setIsDelete("1");
        user.setUserid(Integer.parseInt(id));
        userMapper.updateByPrimaryKeySelective(user);
        logger.info("正在调用deleteUser 方法");
        DeleteUserModel deleteUserModel = new DeleteUserModel();
        return deleteUserModel;
    }

    @Override
    public ModifyUserModel modifyUser(User user) {
        ModifyUserModel modifyUserModel = new ModifyUserModel();
        Example example = new Example(User.class);
        example.createCriteria()
                .andEqualTo("userid",user.getUserid());
        userMapper.updateByExampleSelective(user,example);
        modifyUserModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
        modifyUserModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        return modifyUserModel;
    }

    @Override
    public QryUserInfoResModel qryUserInfo(User user) {
        QryUserInfoResModel qryUserInfoResModel = new QryUserInfoResModel();
        try {
            User user1 = userMapper.selectByPrimaryKey(user);
            qryUserInfoResModel.setUser(user1);
            qryUserInfoResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryUserInfoResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryUserInfoResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryUserInfoResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryUserInfoResModel;
    }
}
