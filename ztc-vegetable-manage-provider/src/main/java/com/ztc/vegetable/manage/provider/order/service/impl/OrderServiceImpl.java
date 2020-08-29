package com.ztc.vegetable.manage.provider.order.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.order.dto.OrderInfo;
import com.ztc.vegetable.manage.api.order.req.ModefityOrderReqModel;
import com.ztc.vegetable.manage.api.order.req.QryAorderReqModel;
import com.ztc.vegetable.manage.api.order.req.SubOrderReqModel;
import com.ztc.vegetable.manage.api.order.res.ModefityOrderResModel;
import com.ztc.vegetable.manage.api.order.res.QryAorderResModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderInfoResModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderResModel;
import com.ztc.vegetable.manage.api.order.res.SubOrderResModel;
import com.ztc.vegetable.manage.api.order.service.OrderService;
import com.ztc.vegetable.manage.db.mapper.CartMapper;
import com.ztc.vegetable.manage.db.mapper.SorderMapper;
import com.ztc.vegetable.manage.db.mapper.UserMapper;
import com.ztc.vegetable.manage.db.model.Cart;
import com.ztc.vegetable.manage.db.model.Sorder;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import com.ztc.vegetable.manage.provider.order.dao.QryOrderInfoDAO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    public final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private SorderMapper SorderMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QryOrderInfoDAO qryOrderInfoDAO;

    @Override
    public QryOrderResModel qryOrder(User user) {
        QryOrderResModel qrySorderResModel = new QryOrderResModel();
        try {
            //先查询该条记录是否存在
            Sorder Sorder = new Sorder();
            Sorder.setUserid(user.getUserid().toString());
            Sorder.setIsDelete("0");
            List<Sorder> select = SorderMapper.select(Sorder);
            if (null != select && select.size() != 0) {
                List<OrderInfo> infos = new ArrayList<>();
                for (Sorder Sorder1 : select) {
                    Cart cart = new Cart();
                    cart.setOrderid(Sorder1.getOrderid());
                    List<Cart> select1 = cartMapper.select(cart);
                    OrderInfo orderInfo = new OrderInfo();
                    orderInfo.setPrice(Sorder1.getPprice());
                    orderInfo.setPhone(Sorder1.getPhone());
                    orderInfo.setCarts(select1);
                    User user1 = userMapper.selectByPrimaryKey(user);
                    orderInfo.setName(user1.getUsername());
                    orderInfo.setAdress(user1.getAdress());
                    infos.add(orderInfo);
                }
                qrySorderResModel.setOrderInfo(infos);
            }
            qrySorderResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qrySorderResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qrySorderResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qrySorderResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qrySorderResModel;
    }

    @Override
    public SubOrderResModel subOrder(SubOrderReqModel subSorderReqModel) {
        SubOrderResModel subSorderResModel = new SubOrderResModel();
        try {
            //先查询该条记录是否存在
            Sorder Sorder = new Sorder();
            Sorder.setUserid(subSorderReqModel.getUid().toString());
            Sorder.setOrderid(subSorderReqModel.getOrderId());
            Sorder Sorder1 = SorderMapper.selectOne(Sorder);
            Sorder1.setIsDelete("1");
            SorderMapper.updateByPrimaryKeySelective(Sorder1);
            subSorderResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            subSorderResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            subSorderResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            subSorderResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return subSorderResModel;
    }

    @Override
    public QryAorderResModel qryOrderByAgent(QryAorderReqModel qryAorderReqModel) {
        QryAorderResModel qryAorderResModel = new QryAorderResModel();
        try {
            //先查询该条记录是否存在
            int pageSize = (0 == qryAorderReqModel.getPageSize() ? 10 : qryAorderReqModel.getPageSize());
            int pageNum = (0 == qryAorderReqModel.getPageNum() ? 1 : qryAorderReqModel.getPageNum());
            PageHelper.startPage(pageNum, pageSize);
            List<Sorder> sorders = qryOrderInfoDAO.qryOrderInfo(qryAorderReqModel);
            PageInfo<Sorder> sorderPageInfo = new PageInfo<>(sorders);
            qryAorderResModel.setSorderList(sorderPageInfo);
            qryAorderResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryAorderResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryAorderResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryAorderResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryAorderResModel;
    }

    @Override
    public QryOrderInfoResModel qryOrderInfoByAgent(String OrderId) {
        QryOrderInfoResModel qryOrderInfoResModel = new QryOrderInfoResModel();
        try {
            Cart cart = new Cart();
            cart.setOrderid(OrderId);
            List<Cart> select = cartMapper.select(cart);
            Sorder sorder = new Sorder();
            sorder.setOrderid(OrderId);
            Sorder sorder1 = SorderMapper.selectOne(sorder);
            OrderInfo orderInfo = new OrderInfo();
            User user1 = userMapper.selectByPrimaryKey(sorder1.getUserid());
            orderInfo.setName(user1.getUsername());
            orderInfo.setAdress(sorder1.getAdress());
            orderInfo.setTimes(sorder1.getCreatedDate());
            orderInfo.setOrderId(sorder1.getOrderid());
            orderInfo.setPhone(sorder1.getPhone());
            orderInfo.setPrice(sorder1.getPprice());
            orderInfo.setCarts(select);
            qryOrderInfoResModel.setOrderInfo(orderInfo);
            qryOrderInfoResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryOrderInfoResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryOrderInfoResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryOrderInfoResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryOrderInfoResModel;
    }

    @Override
    public ModefityOrderResModel modifyOrder(ModefityOrderReqModel modefityOrderReqModel) {
        ModefityOrderResModel modefityOrderResModel = new ModefityOrderResModel();
        try {

            Example example = new Example(Sorder.class);
            example.createCriteria()
                    .andEqualTo("orderid",modefityOrderReqModel.getId());
            Sorder sorder = new Sorder();
            sorder.setPhone(modefityOrderReqModel.getPhone());
            sorder.setAdress(modefityOrderReqModel.getAdress());
            int i = SorderMapper.updateByExampleSelective(sorder, example);
            modefityOrderResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            modefityOrderResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            modefityOrderResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            modefityOrderResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return modefityOrderResModel;
    }
}