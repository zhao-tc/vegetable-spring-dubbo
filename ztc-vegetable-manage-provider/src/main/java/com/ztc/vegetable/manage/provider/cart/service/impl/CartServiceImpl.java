package com.ztc.vegetable.manage.provider.cart.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.cart.req.AddToCartReqModel;
import com.ztc.vegetable.manage.api.cart.req.CartToOrderReqModel;
import com.ztc.vegetable.manage.api.cart.req.SubCartReqModel;
import com.ztc.vegetable.manage.api.cart.res.AddToCartModel;
import com.ztc.vegetable.manage.api.cart.res.CartToOrderResModel;
import com.ztc.vegetable.manage.api.cart.res.QryCartListResModel;
import com.ztc.vegetable.manage.api.cart.res.SubCartResModel;
import com.ztc.vegetable.manage.api.cart.service.CartService;
import com.ztc.vegetable.manage.db.mapper.AdressMapper;
import com.ztc.vegetable.manage.db.mapper.CartMapper;
import com.ztc.vegetable.manage.db.mapper.SorderMapper;
import com.ztc.vegetable.manage.db.model.Adress;
import com.ztc.vegetable.manage.db.model.Cart;
import com.ztc.vegetable.manage.db.model.ShopList;
import com.ztc.vegetable.manage.db.model.Sorder;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    public final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private SorderMapper SorderMapper;
    @Autowired
    private AdressMapper adressMapper;
    @Override
    public AddToCartModel addToCart(AddToCartReqModel addToCartReqModel) {
        AddToCartModel addToCartModel = new AddToCartModel();
        try {
            //先查询该条记录是否存在
            Cart cart = new Cart();
            ShopList shopList = addToCartReqModel.getShopList();
            Integer uid = addToCartReqModel.getUid();
            Integer Num = addToCartReqModel.getNum();
            cart.setGid(shopList.getId());
            cart.setParentId(uid);
            cart.setIsDelete("0");
            Cart cart1 = cartMapper.selectOne(cart);
            if(null == Num || 0 == Num){
                Num = 1;
            }
            if(null == cart1){
                 cart.setpName(shopList.getName());
                 cart.setpPrice(shopList.getPrice());

                     cart.setpTotal(shopList.getPrice()*Num);
                     cart.setQuantity(Num);
                 cart.setpSrc(shopList.getSrc());
                 cart.setParentId(addToCartReqModel.getUid());
                 cartMapper.insert(cart);
             }else {
                Integer num =  cart1.getQuantity() + Num;
                cart1.setQuantity(num);
                cart1.setpTotal(cart1.getpPrice()*num);
                cartMapper.updateByPrimaryKeySelective(cart1);
             }
            addToCartModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            addToCartModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            addToCartModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            addToCartModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return addToCartModel;
    }

    @Override
    public QryCartListResModel showCartList(Integer uid) {
        QryCartListResModel qryCartListResModel = new QryCartListResModel();
        try {
            Cart cart = new Cart();
            cart.setParentId(uid);
            cart.setIsDelete("0");
            PageHelper.startPage(1, 20);
            List<Cart> select = cartMapper.select(cart);
            PageInfo<Cart> wishList = new PageInfo<>(select);
            qryCartListResModel.setPageInfo(wishList);
            qryCartListResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryCartListResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryCartListResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryCartListResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryCartListResModel;
    }

    @Override
    public SubCartResModel subCart(SubCartReqModel subCartReqModel) {
        SubCartResModel subCartResModel = new SubCartResModel();
        try {
            Cart cart = subCartReqModel.getCart();
            cart.setIsDelete("1");
            cartMapper.updateByPrimaryKeySelective(cart);
            subCartResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            subCartResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            subCartResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            subCartResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return subCartResModel;
    }

    @Override
    public CartToOrderResModel toOrder(CartToOrderReqModel cartToSorderModelReq) {
        CartToOrderResModel cartToSorderResModel = new CartToOrderResModel();
        try {
            List<Cart> carts = cartToSorderModelReq.getCarts();
            if(null != carts && carts.size() != 0) {
                String SorderId = System.currentTimeMillis() + "" + cartToSorderModelReq.getUid();
                for (Cart cart : carts) {
                    cart.setOrderid(SorderId);
                    cart.setIsDelete("1");
                    cartMapper.updateByPrimaryKey(cart);
                }
                Sorder Sorder = new Sorder();
                Integer uid = cartToSorderModelReq.getUid();
                String name = cartToSorderModelReq.getName();
                Sorder.setUserid(uid.toString());
                Sorder.setUsername(name);
                Adress adress = new Adress();
                adress.setUserid(uid.toString());
                adress.setIsDelete("0");

                List<Adress> select = adressMapper.select(adress);
                Adress adress1 = select.get(0);

                Sorder.setPhone(adress1.getNum());
                Sorder.setAdress(adress1.getAdress());
                Sorder.setOrderid(SorderId);
                Sorder.setPprice(cartToSorderModelReq.getPrice());
                SorderMapper.insert(Sorder);
            }
            cartToSorderResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            cartToSorderResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            cartToSorderResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            cartToSorderResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return cartToSorderResModel;
    }
}
