package com.ztc.vegetable.manage.restful.good.controller;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.goods.service.GoodService;
import com.ztc.vegetable.manage.api.goods.service.req.AddGoodReqModel;
import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.api.goods.service.res.AddGoodResModel;
import com.ztc.vegetable.manage.api.goods.service.res.DeleteGoodModel;
import com.ztc.vegetable.manage.api.goods.service.res.ModifyGoodModel;
import com.ztc.vegetable.manage.api.goods.service.res.QryGoodListResModel;
import com.ztc.vegetable.manage.db.model.ShopList;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
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
@RequestMapping("/good")
public class GoodController extends BaseRestfulController {

    @Reference
    private GoodService goodService;

    @PostMapping("/showAllGoods")
    public ResponseEntity<ResultModel> showAllGoods(@RequestBody QryGoodListReqModel qryGoodListReqModel) {
        try {
            logger.info("调用showAllGoods展示用户接口请求报文："+ JSON.toJSONString(qryGoodListReqModel));
            QryGoodListResModel qryGoodListResModel = goodService.qryGoodList(qryGoodListReqModel);
            logger.info("调用showAllGoods展示用户接口返回报文：" + JSON.toJSONString(qryGoodListResModel));
            qryGoodListResModel.setResultCode("0");
            qryGoodListResModel.setResultMessage("调用成功");
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(qryGoodListResModel.getResultCode())) {
                return this.resultResponseSuccess(qryGoodListResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryGoodListResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用showAllGoods展示用户接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @PostMapping("/modifyGood")
    public ResponseEntity<ResultModel> modifyGood( @RequestBody ShopList shopList) {
        try {
            logger.info("调用modifyGood接口请求报文：" + shopList);
            ModifyGoodModel modifyGoodModel = goodService.modifyGood(shopList);
            modifyGoodModel.setResultCode("0");
            modifyGoodModel.setResultMessage("调用成功");
            logger.info("调用modifyGood接口返回报文：" + JSON.toJSONString(modifyGoodModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(modifyGoodModel.getResultCode())) {
                return this.resultResponseSuccess(modifyGoodModel);
            } else {
                return this.resultResponseFault(new ContentModel(modifyGoodModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用modifyGood接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @GetMapping("/deleteGood")
    public ResponseEntity<ResultModel> deleteGood(String id) {
        try {
            logger.info("调用deleteUser接口请求报文：" + id);
            DeleteGoodModel deleteGoodModel = goodService.deleteGood(id);
            deleteGoodModel.setResultCode("0");
            deleteGoodModel.setResultMessage("调用成功");
            logger.info("调用deleteGood接口返回报文：" + JSON.toJSONString(deleteGoodModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(deleteGoodModel.getResultCode())) {
                return this.resultResponseSuccess(deleteGoodModel);
            } else {
                return this.resultResponseFault(new ContentModel(deleteGoodModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("deleteGood-restful接口请求异常:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @PostMapping("/addGood")
    public ResponseEntity<ResultModel> addGood( @RequestBody ShopList shopList) {
        try {
            logger.info("调用addGood接口请求报文：" + JSON.toJSONString(shopList));
            AddGoodReqModel addGoodReqModel = new AddGoodReqModel();
            addGoodReqModel.setGood(shopList);
            AddGoodResModel addGoodResModel = goodService.addGood(addGoodReqModel);
            logger.info("调用addGood接口返回报文：" + JSON.toJSONString(addGoodResModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(addGoodResModel.getResultCode())) {
                return this.resultResponseSuccess(addGoodResModel);
            } else {
                return this.resultResponseFault(new ContentModel(addGoodResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("deleteGood-restful接口请求异常:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
}
