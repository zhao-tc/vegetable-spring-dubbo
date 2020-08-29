package com.ztc.vegetable.manage.restful.init.controller;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.api.init.res.NewDataModel;
import com.ztc.vegetable.manage.api.init.service.NewDataService;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.constant.RestfulConstant;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/data")
public class NewDataController extends BaseRestfulController {
    @Reference
    private NewDataService newDataService;
    @PostMapping("/newData")
    public ResponseEntity<ResultModel> newDatas(){
         try {
        logger.info("调用查询新增数据接口请求报文：");
             NewDataModel newDataModel = newDataService.newDatas();
             newDataModel.setResultCode("0");
             newDataModel.setResultMessage("调用成功");
        logger.info("调用查询新增数据接口返回报文：" + JSON.toJSONString(newDataModel));
        if (RestfulConstant.RESULT_CODE_SUCCESS.equals(newDataModel.getResultCode())) {
            return this.resultResponseSuccess(newDataModel);
        } else {
            return this.resultResponseFault(new ContentModel(newDataModel.getResultMessage()));
        }
    } catch (Exception e) {
        logger.error("调用查询新增数据接口请求异常:" + e.getMessage(), e);
        return this.resultResponseFault(new ContentModel("系统异常，请稍后重试！"));
    }
}
}
