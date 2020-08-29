package com.ztc.vegetable.manage.restful.client.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ztc.vegetable.manage.api.cart.req.AddToCartReqModel;
import com.ztc.vegetable.manage.api.cart.req.CartToOrderReqModel;
import com.ztc.vegetable.manage.api.cart.req.SubCartReqModel;
import com.ztc.vegetable.manage.api.cart.res.AddToCartModel;
import com.ztc.vegetable.manage.api.cart.res.CartToOrderResModel;
import com.ztc.vegetable.manage.api.cart.res.QryCartListResModel;
import com.ztc.vegetable.manage.api.cart.res.SubCartResModel;
import com.ztc.vegetable.manage.api.cart.service.CartService;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.goods.service.GoodService;
import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.api.goods.service.res.QryGoodListResModel;
import com.ztc.vegetable.manage.api.goods.service.res.QryMainPageModel;
import com.ztc.vegetable.manage.api.order.req.SubOrderReqModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderResModel;
import com.ztc.vegetable.manage.api.order.res.SubOrderResModel;
import com.ztc.vegetable.manage.api.order.service.OrderService;
import com.ztc.vegetable.manage.api.user.service.UserService;
import com.ztc.vegetable.manage.api.user.service.res.ModifyUserModel;
import com.ztc.vegetable.manage.api.user.service.res.QryUserInfoResModel;
import com.ztc.vegetable.manage.api.wish.req.AddToWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.QryWishListReqModel;
import com.ztc.vegetable.manage.api.wish.req.SubWishReqModel;
import com.ztc.vegetable.manage.api.wish.req.WAddToCartReqModel;
import com.ztc.vegetable.manage.api.wish.res.AddToWishResModel;
import com.ztc.vegetable.manage.api.wish.res.QryWishListResModel;
import com.ztc.vegetable.manage.api.wish.res.SubWishResModel;
import com.ztc.vegetable.manage.api.wish.res.WAddToCartResModel;
import com.ztc.vegetable.manage.api.wish.service.WishService;
import com.ztc.vegetable.manage.db.model.ShopList;
import com.ztc.vegetable.manage.db.model.User;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.annotation.UserLoginToken;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import com.ztc.vegetable.manage.restful.user.controller.req.MdifyUserReqModel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zhaotiancheng
 * @Class UserController
 * @date 2020-01-2020/1/2 12:23
 */
@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/client")
public class ClientController extends BaseRestfulController {
    @Reference
    private GoodService goodService;
    @Reference
    private CartService cartService;
    @Reference
    private WishService wishService;
    @Reference
    private OrderService  orderService;
    @Reference
    private UserService userService;
    @GetMapping("/showMainPage")
    public ResponseEntity<ResultModel> qryMainPageModel() {
        try {
            logger.info("调用qryMainPageModel接口请求报文：");
            QryMainPageModel qryMainPageModel = goodService.qryMainPage();
            logger.info("调用qryMainPageModel接口返回报文：" + JSON.toJSONString(qryMainPageModel));
            qryMainPageModel.setResultCode("0");
            qryMainPageModel.setResultMessage("调用成功");
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(qryMainPageModel.getResultCode())) {
                return this.resultResponseSuccess(qryMainPageModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryMainPageModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用qryMainPageModel展示用户接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @PostMapping("/showShipList")
    public ResponseEntity<ResultModel> showShipList(@RequestBody QryGoodListReqModel qryGoodListReqModel) {
        try {
            logger.info("调用商品分页查询接口请求报文："+JSON.toJSONString(qryGoodListReqModel));
            QryGoodListResModel qryGoodListResModel = goodService.qryGoodList(qryGoodListReqModel);
            logger.info("调用showShipList接口返回报文：" + JSON.toJSONString(qryGoodListResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(qryGoodListResModel.getResultCode())) {
                return this.resultResponseSuccess(qryGoodListResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryGoodListResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用商品分页查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @PostMapping("/addToCart")
    public ResponseEntity<ResultModel> addToCart(@RequestBody String jsonObject, User user) {
        logger.info("调用添加至购物车接口请求报文：" + JSON.toJSONString(jsonObject) );
        logger.info("调用添加至购物车接口请求报文：" + JSON.toJSONString(user) );
        try {
            JSONObject Jobj = JSONObject.parseObject(jsonObject);
            String versionInfoStr = Jobj.getString("shopList");
            String Num = Jobj.getString("Num");
            ShopList shopList = JSONObject.parseObject(versionInfoStr, ShopList.class);
            Integer Num2 = JSONObject.parseObject(Num, Integer.class);
            AddToCartReqModel addToCartReqModel = new AddToCartReqModel();
            addToCartReqModel.setShopList(shopList);
            addToCartReqModel.setUid(user.getUserid());
            addToCartReqModel.setNum(Num2);
            AddToCartModel addToCartModel = cartService.addToCart(addToCartReqModel);
            logger.info("调用showShipList接口返回报文：" + JSON.toJSONString(addToCartModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(addToCartModel.getResultCode())) {
                return this.resultResponseSuccess(addToCartModel);
            } else {
                return this.resultResponseFault(new ContentModel(addToCartModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用商品分页查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @PostMapping("/showCartList")
    public ResponseEntity<ResultModel> showCartList(User user) {
        try {
            Integer userid = user.getUserid();
//            logger.info("调用愿望清单查询接口请求报文："+JSON.toJSONString(qryWishListReqModel));
            QryCartListResModel qryCartListResModel = cartService.showCartList(userid);
            logger.info("调用购物车接口返回报文：" + JSON.toJSONString(qryCartListResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(qryCartListResModel.getResultCode())) {
                return this.resultResponseSuccess(qryCartListResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryCartListResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用购物车查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @PostMapping("/subCart")
    public ResponseEntity<ResultModel> subCart(@RequestBody SubCartReqModel subCartReqModel, User user) {
        try {

            logger.info("调用购物车删除接口请求报文："+JSON.toJSONString(subCartReqModel));
            SubCartResModel subCartResModel = cartService.subCart(subCartReqModel);
            logger.info("调用购物车删除接口返回报文：" + JSON.toJSONString(subCartResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(subCartResModel.getResultCode())) {
                return this.resultResponseSuccess(subCartResModel);
            } else {
                return this.resultResponseFault(new ContentModel(subCartResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用购物车删除接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @PostMapping("/toOrder")
    public ResponseEntity<ResultModel> toOrder(@RequestBody CartToOrderReqModel cartToOrderReqModel, User user) {
        try {
            cartToOrderReqModel.setUid(user.getUserid());
            cartToOrderReqModel.setName(user.getUsername());
            logger.info("调用生成订单接口请求报文："+JSON.toJSONString(cartToOrderReqModel));
            CartToOrderResModel cartToOrderResModel = cartService.toOrder(cartToOrderReqModel);
            logger.info("调用生成订单接口返回报文：" + JSON.toJSONString(cartToOrderResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(cartToOrderResModel.getResultCode())) {
                return this.resultResponseSuccess(cartToOrderResModel);
            } else {
                return this.resultResponseFault(new ContentModel(cartToOrderResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用生成订单接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }


    @UserLoginToken
    @PostMapping("/addToWish")
    public ResponseEntity<ResultModel> addToWish(@RequestBody String jsonObject, User user) {
        logger.info("调用添加至愿望清单接口请求报文：" + JSON.toJSONString(jsonObject) );
        logger.info("调用添加至愿望清单接口请求报文：" + JSON.toJSONString(user) );
        try {
            JSONObject Jobj = JSONObject.parseObject(jsonObject);
            String versionInfoStr = Jobj.getString("shopList");
            ShopList shopList = JSONObject.parseObject(versionInfoStr, ShopList.class);
            AddToWishReqModel addToWishReqModel = new AddToWishReqModel();
            addToWishReqModel.setShopList(shopList);
            addToWishReqModel.setUid(user.getUserid());
            AddToWishResModel addToWishResModel = wishService.addToWish(addToWishReqModel);
            logger.info("调用添加至愿望清单接口返回报文：" + JSON.toJSONString(addToWishResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(addToWishResModel.getResultCode())) {
                return this.resultResponseSuccess(addToWishResModel);
            } else {
                return this.resultResponseFault(new ContentModel(addToWishResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用添加至愿望清单查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
    @UserLoginToken
    @PostMapping("/showWishList")
    public ResponseEntity<ResultModel> showWishList(@RequestBody QryWishListReqModel qryWishListReqModel,User user) {
        try {
            qryWishListReqModel.setUid(user.getUserid());
//            logger.info("调用愿望清单查询接口请求报文："+JSON.toJSONString(qryWishListReqModel));
            QryWishListResModel qryWishListResModel = wishService.qryWishList(qryWishListReqModel);
            logger.info("调用愿望清单查询接口返回报文：" + JSON.toJSONString(qryWishListResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(qryWishListResModel.getResultCode())) {
                return this.resultResponseSuccess(qryWishListResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryWishListResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用愿望清单查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
    @UserLoginToken
    @PostMapping("/wishAddToCart")
    public ResponseEntity<ResultModel> wishAddToCart(@RequestBody WAddToCartReqModel wAddToCartReqModel, User user) {
        try {
            wAddToCartReqModel.setUid(user.getUserid());
            logger.info("调用愿望清单添加到购物车接口请求报文："+JSON.toJSONString(wAddToCartReqModel));
            WAddToCartResModel wAddToCartResModel = wishService.addToCart(wAddToCartReqModel);
            logger.info("调用愿望清单添加到购物车接口返回报文：" + JSON.toJSONString(wAddToCartResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(wAddToCartResModel.getResultCode())) {
                return this.resultResponseSuccess(wAddToCartResModel);
            } else {
                return this.resultResponseFault(new ContentModel(wAddToCartResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用愿望清单添加到购物车接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
    @UserLoginToken
    @PostMapping("/subWish")
    public ResponseEntity<ResultModel> subWish(@RequestBody SubWishReqModel subWishReqModel, User user) {
        try {
            subWishReqModel.setUid(user.getUserid());
            logger.info("调用愿望清单删除接口请求报文："+JSON.toJSONString(subWishReqModel));
            SubWishResModel subWishResModel = wishService.subWish(subWishReqModel);
            logger.info("调用愿望清单删除接口返回报文：" + JSON.toJSONString(subWishResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(subWishResModel.getResultCode())) {
                return this.resultResponseSuccess(subWishResModel);
            } else {
                return this.resultResponseFault(new ContentModel(subWishResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用愿望清单删除接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @GetMapping("/qryOrder")
    public ResponseEntity<ResultModel> qryOrder(User user) {
        try {
            logger.info("调用订单查询接口请求报文："+JSON.toJSONString(user));
            QryOrderResModel qryOrderResModel = orderService.qryOrder(user);
            logger.info("调用订单查询接口返回报文：" + JSON.toJSONString(qryOrderResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(qryOrderResModel.getResultCode())) {
                return this.resultResponseSuccess(qryOrderResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryOrderResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用订单查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @UserLoginToken
    @PostMapping("/subOrder")
    public ResponseEntity<ResultModel> subOrder(@RequestBody SubOrderReqModel subOrderReqModel , User user) {
        try {
            subOrderReqModel.setUid(user.getUserid());
            logger.info("调用订单删除接口请求报文："+JSON.toJSONString(subOrderReqModel));
            SubOrderResModel subOrderResModel = orderService.subOrder(subOrderReqModel);
            logger.info("调用订单删除接口返回报文：" + JSON.toJSONString(subOrderResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(subOrderResModel.getResultCode())) {
                return this.resultResponseSuccess(subOrderResModel);
            } else {
                return this.resultResponseFault(new ContentModel(subOrderResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用订单删除接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
    @UserLoginToken
    @PostMapping("/userinfo")
    public ResponseEntity<ResultModel> userinfo(User user) {
        try {

            logger.info("调用用户信息查询接口请求报文："+JSON.toJSONString(user));
            QryUserInfoResModel qryUserInfoResModel = userService.qryUserInfo(user);
            logger.info("调用用户信息查询接口返回报文：" + JSON.toJSONString(qryUserInfoResModel));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(qryUserInfoResModel.getResultCode())) {
                return this.resultResponseSuccess(qryUserInfoResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryUserInfoResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用用户信息查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }



    @UserLoginToken
    @PostMapping("/modifyUserInfo")
    public ResponseEntity<ResultModel> modifyUserInfo(@RequestBody MdifyUserReqModel mdifyUserReqModel, User user) {
        try {
            logger.info("调用用户信息查询接口请求报文："+JSON.toJSONString(user));
            user.setUsername(mdifyUserReqModel.getUserName());
            user.setPhone(mdifyUserReqModel.getPhone());
            user.setAdress(mdifyUserReqModel.getAdress());
            ModifyUserModel modifyUserModel = userService.modifyUser(user);
            logger.info("调用用户信息查询接口返回报文：" + JSON.toJSONString(user));
            if (!RestfulConstant.RESULT_CODE_SUCCESS.equals(modifyUserModel.getResultCode())) {
                return this.resultResponseSuccess(modifyUserModel);
            } else {
                return this.resultResponseFault(new ContentModel(modifyUserModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用用户信息查询接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
}
