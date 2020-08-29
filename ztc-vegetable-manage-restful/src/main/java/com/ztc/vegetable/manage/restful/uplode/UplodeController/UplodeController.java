package com.ztc.vegetable.manage.restful.uplode.UplodeController;

import com.alibaba.fastjson.JSON;
import com.ztc.vegetable.manage.restful.BaseRestfulController;
import com.ztc.vegetable.manage.restful.comment.bo.OSSBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@ResponseBody
@CrossOrigin(origins = "*")
@RequestMapping("/uplode")
public class UplodeController extends BaseRestfulController {
    @Autowired
    private OSSBO ossbo;

    @PostMapping("/uplodeImges")
    public String uplodeImges(@RequestParam("file") MultipartFile file) {
        try {
            ossbo.init();
            String name = ossbo.uploadImg2Oss(file);
            String imgUrl = ossbo.getImgUrl(name);
            String[] split = imgUrl.split("\\?");
            logger.info("调用showAllGoods展示用户接口返回报文：" + JSON.toJSONString(split[0]));
            return split[0];
        } catch (Exception e) {
            logger.error("调用showAllGoods展示用户接口:" + e.getMessage(), e);
            return null;
        }
    }
}
