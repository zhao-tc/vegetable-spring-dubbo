package com.ztc.vegetable.manage.provider.wish.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.cart.req.AddToCartReqModel;
import com.ztc.vegetable.manage.api.cart.service.CartService;
import com.ztc.vegetable.manage.api.wish.req.AddToWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.QryWishListReqModel;
import com.ztc.vegetable.manage.api.wish.req.SubWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.WAddToCartReqModel;
import com.ztc.vegetable.manage.api.wish.res.AddToWishResModel;
import com.ztc.vegetable.manage.api.wish.res.QryWishListResModel;
import com.ztc.vegetable.manage.api.wish.res.SubWishResModel;
import com.ztc.vegetable.manage.api.wish.res.WAddToCartResModel;
import com.ztc.vegetable.manage.api.wish.service.WishService;
import com.ztc.vegetable.manage.db.mapper.ShopListMapper;
import com.ztc.vegetable.manage.db.mapper.WishMapper;
import com.ztc.vegetable.manage.db.model.ShopList;
import com.ztc.vegetable.manage.db.model.Wish;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class WishServiceImpl implements WishService {
    public final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private WishMapper wishMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private ShopListMapper shopListMapper;
    @Override
    public AddToWishResModel addToWish(AddToWishReqModel addToWishReqModel) {
        AddToWishResModel addToWishResModel = new AddToWishResModel();
        try {
            //先查询该条记录是否存在
            Wish wish = new Wish();
            ShopList shopList = addToWishReqModel.getShopList();
            Integer uid = addToWishReqModel.getUid();
            wish.setGid(shopList.getId());
            wish.setParentId(uid);
            wish.setIsDelete("0");
            Wish wish1 = wishMapper.selectOne(wish);
            if(null == wish1){
                wish.setpName(shopList.getName());
                wish.setpPrice(shopList.getPrice());
                wish.setSrc(shopList.getSrc());
                wish.setParentId(addToWishReqModel.getUid());
                wishMapper.insert(wish);
            }
            addToWishResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            addToWishResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            addToWishResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            addToWishResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return addToWishResModel;
    }

    @Override
    public QryWishListResModel qryWishList(QryWishListReqModel qryWishListReqModel) {
        QryWishListResModel qryWishListResModel = new QryWishListResModel();
        try {
            Wish wish = new Wish();
            wish.setParentId(qryWishListReqModel.getUid());
            wish.setIsDelete("0");
            Integer pageSize1 = qryWishListReqModel.getPageSize();

            int pageSize = (null == qryWishListReqModel.getPageSize() ? 15 : qryWishListReqModel.getPageSize());
            int pageNum = (null == qryWishListReqModel.getPageNum() ? 1 : qryWishListReqModel.getPageNum());
            PageHelper.startPage(pageNum, pageSize);
            List<Wish> select = wishMapper.select(wish);
            PageInfo<Wish> wishList = new PageInfo<>(select);
            qryWishListResModel.setPageInfo(wishList);
            qryWishListResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryWishListResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryWishListResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryWishListResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryWishListResModel;
    }

    @Override
    public WAddToCartResModel addToCart(WAddToCartReqModel waddToCartReqModel) {
        WAddToCartResModel wAddToCartResModel = new WAddToCartResModel();
        try {
            Wish wish = waddToCartReqModel.getWish();
            AddToCartReqModel addToCart = new AddToCartReqModel();
            addToCart.setNum(1);
            addToCart.setUid(waddToCartReqModel.getUid());
            ShopList shopList = shopListMapper.selectByPrimaryKey(wish.getGid());
            addToCart.setShopList(shopList);
            cartService.addToCart(addToCart);
            wish.setIsDelete("1");
            wishMapper.updateByPrimaryKeySelective(wish);
            wAddToCartResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            wAddToCartResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        }catch (TradeException te){
            wAddToCartResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            wAddToCartResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return wAddToCartResModel;
    }

    @Override
    public SubWishResModel subWish(SubWishReqModel subWishReqModel) {
        SubWishResModel subWishResModel = new SubWishResModel();
        try {
            Wish wish = subWishReqModel.getWish();
            wish.setIsDelete("1");
            wishMapper.updateByPrimaryKeySelective(wish);
            subWishResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            subWishResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        }catch (TradeException te){
            subWishResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            subWishResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return subWishResModel;
    }
}
