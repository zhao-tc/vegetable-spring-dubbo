package com.ztc.vegetable.manage.restful.order.controller;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.order.req.ModefityOrderReqModel;
import com.ztc.vegetable.manage.api.order.req.QryAorderReqModel;
import com.ztc.vegetable.manage.api.order.res.ModefityOrderResModel;
import com.ztc.vegetable.manage.api.order.res.QryAorderResModel;
import com.ztc.vegetable.manage.api.order.res.QryOrderInfoResModel;
import com.ztc.vegetable.manage.api.order.service.OrderService;
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

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/order")
public class OrderController extends BaseRestfulController {

    @Reference
    private OrderService orderService;
    @PostMapping ("/showAllOrders")
    public ResponseEntity<ResultModel> showAllOrders(@RequestBody QryAorderReqModel qryAorderReqModel) {
        try {
            logger.info("调用查询所有订单接口请求报文：" +JSON.toJSONString(qryAorderReqModel));
            QryAorderResModel qryAorderResModel = orderService.qryOrderByAgent(qryAorderReqModel);
            logger.info("调用查询所有订单接口返回报文：" + JSON.toJSONString(qryAorderResModel));
            qryAorderResModel.setResultCode("0");
            qryAorderResModel.setResultMessage("调用成功");
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(qryAorderResModel.getResultCode())) {
                return this.resultResponseSuccess(qryAorderResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryAorderResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用查询所有订单接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @GetMapping("/qryOrderInfo")
    public ResponseEntity<ResultModel> qryOrderInfo(String orderId) {
        try {
            logger.info("调用查询订单详情接口请求报文：" +JSON.toJSONString(orderId));
            QryOrderInfoResModel qryOrderInfoResModel = orderService.qryOrderInfoByAgent(orderId);
            logger.info("调用查询订单详情接口返回报文：" + JSON.toJSONString(qryOrderInfoResModel));
            qryOrderInfoResModel.setResultCode("0");
            qryOrderInfoResModel.setResultMessage("调用成功");
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(qryOrderInfoResModel.getResultCode())) {
                return this.resultResponseSuccess(qryOrderInfoResModel);
            } else {
                return this.resultResponseFault(new ContentModel(qryOrderInfoResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用查询订单详情接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }

    @PostMapping("/modefyOrder")
    public ResponseEntity<ResultModel> modefyOrder(@RequestBody ModefityOrderReqModel modefityOrderReqModel) {
        try {
            logger.info("调用修改订单详情接口请求报文：" +JSON.toJSONString(modefityOrderReqModel));
            ModefityOrderResModel modefityOrderResModel = orderService.modifyOrder(modefityOrderReqModel);
            logger.info("调用修改订单详情接口返回报文：" + JSON.toJSONString(modefityOrderResModel));
            if (RestfulConstant.RESULT_CODE_SUCCESS.equals(modefityOrderResModel.getResultCode())) {
                return this.resultResponseSuccess(modefityOrderResModel);
            } else {
                return this.resultResponseFault(new ContentModel(modefityOrderResModel.getResultMessage()));
            }
        } catch (Exception e) {
            logger.error("调用修改订单详情接口:" + e.getMessage(), e);
            return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
        }
    }
}
