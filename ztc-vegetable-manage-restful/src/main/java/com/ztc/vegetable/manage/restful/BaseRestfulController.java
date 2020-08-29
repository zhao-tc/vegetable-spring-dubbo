package com.ztc.vegetable.manage.restful;

import com.alibaba.fastjson.JSONObject;
import com.ztc.vegetable.manage.api.dto.ContentModel;
import com.ztc.vegetable.manage.restful.comment.dto.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * @author zhaotiancheng
 * @Class BaseRestfulController
 * @date 2020-01-2020/1/2 13:44
 */
public class BaseRestfulController {
    public final Log logger =  LogFactory.getLog(this.getClass());

    public BaseRestfulController() {
    }

    public ResponseEntity<ResultModel> resultResponse(Object... dto) {
        this.logger.info("resultResponse:" + JSONObject.toJSONString(dto));
        return null != dto && dto.length > 0 ? new ResponseEntity(ResultModel.ok(dto[0]), HttpStatus.OK) : new ResponseEntity(ResultModel.ok((Object)null), HttpStatus.OK);
    }

    public ResponseEntity<ResultModel> resultResponse(ContentModel... contentModel) {
        this.logger.info("resultResponse:" + JSONObject.toJSONString(contentModel));
        return null != contentModel && contentModel.length > 0 ? new ResponseEntity(ResultModel.ok(contentModel[0]), HttpStatus.OK) : new ResponseEntity(ResultModel.ok((Object)null), HttpStatus.OK);
    }

    public ResponseEntity<ResultModel> resultResponseSuccess(ContentModel... contentModel) {
        if (null != contentModel && contentModel.length > 0) {
            contentModel[0].setResultCode("0");
            this.logger.info("resultResponseSuccess:" +JSONObject.toJSONString(contentModel));
            return new ResponseEntity(ResultModel.ok(contentModel[0]), HttpStatus.OK);
        } else {
            this.logger.info("resultResponseSuccess:" + JSONObject.toJSONString(contentModel));
            return new ResponseEntity(ResultModel.ok((Object)null), HttpStatus.OK);
        }
    }

    public ResponseEntity<ResultModel> resultResponseFault(ContentModel... contentModel) {
        if (null != contentModel && contentModel.length > 0) {
            contentModel[0].setResultCode("1");
            this.logger.info("resultResponseFault:" + JSONObject.toJSONString(contentModel));
            return new ResponseEntity(ResultModel.ok(contentModel[0]), HttpStatus.OK);
        } else {
            this.logger.info("resultResponseFault:" + JSONObject.toJSONString(contentModel));
            return new ResponseEntity(ResultModel.ok((Object)null), HttpStatus.OK);
        }
    }
}
