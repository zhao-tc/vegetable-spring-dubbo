package com.ztc.vegetable.manage.provider.food.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztc.vegetable.manage.api.goods.service.GoodService;
import com.ztc.vegetable.manage.api.goods.service.req.AddGoodReqModel;
import com.ztc.vegetable.manage.api.goods.service.req.QryGoodListReqModel;
import com.ztc.vegetable.manage.api.goods.service.res.*;
import com.ztc.vegetable.manage.db.mapper.ShopListMapper;
import com.ztc.vegetable.manage.db.model.ShopList;
import com.ztc.vegetable.manage.provider.comment.constant.ENUM_RESULT_CODE;
import com.ztc.vegetable.manage.provider.comment.exception.BaseCheckException;
import com.ztc.vegetable.manage.provider.comment.exception.TradeException;
import com.ztc.vegetable.manage.provider.food.dao.MainPageDAO;
import com.ztc.vegetable.manage.provider.food.dao.QryPageDAO;
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
public class GoodServiceImpl implements GoodService {
    public final Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private ShopListMapper shopListMapper;
    @Autowired
    private MainPageDAO mainPageDAO;

    @Autowired
    private QryPageDAO qryPageDAO;

    @Override
    public GoodPageInfoResModel showPageGood(int pageNum) {
        logger.info("正在调用showPageGood 方法");
        PageHelper.startPage(pageNum, 10);
        Example example = new Example(ShopList.class);
        example.createCriteria().andEqualTo("isDelete", "0");
        List<ShopList> shopLists = shopListMapper.selectByExample(example);
        PageInfo<ShopList> shopListPageInfo = new PageInfo<>(shopLists);
        GoodPageInfoResModel goodPageInfoResModel = new GoodPageInfoResModel();
        goodPageInfoResModel.setPageInfo(shopListPageInfo);
        logger.info("调用showPageGood 方法结束");
        return goodPageInfoResModel;
    }

    @Override
    public DeleteGoodModel deleteGood(String id) {
        logger.info("正在调用deleteGood 方法");
        ShopList shopList = new ShopList();
        shopList.setIsDelete("1");
        shopList.setId(Integer.parseInt(id));
        shopListMapper.updateByPrimaryKeySelective(shopList);
        logger.info("正在调用deleteGood 方法");
        DeleteGoodModel deleteGoodModel = new DeleteGoodModel();
        return deleteGoodModel;
    }

    @Override
    public ModifyGoodModel modifyGood(ShopList shopList) {
        ModifyGoodModel modifyGoodModel = new ModifyGoodModel();
        logger.info("正在调用modifyGood 方法");
        shopListMapper.updateByPrimaryKeySelective(shopList);
        logger.info("正在调用deleteUser 方法");
        return modifyGoodModel;
    }

    @Override
    public AddGoodResModel addGood(AddGoodReqModel addGoodReqModel) {
        AddGoodResModel addGoodResModel = new AddGoodResModel();
        try {
            BaseCheckException.checkNotNull(addGoodReqModel.getGood(), "请填写商品信息");
            ShopList good = addGoodReqModel.getGood();
            BaseCheckException.checkNotEmpty(good.getName(), "请填写商品名称");
            BaseCheckException.checkNotNull(good.getPrice(), "请填写商品价格");
            BaseCheckException.checkNotEmpty(good.getDes(), "请填写商品信息描述");
            BaseCheckException.checkNotEmpty(good.getSrc(), "请填写商品图片");
            ShopList shopList = new ShopList();
            shopList.setName(addGoodReqModel.getGood().getName());
            List<ShopList> selectName = shopListMapper.select(shopList);
            if (null != selectName && selectName.size() >= 1) {
                throw new TradeException("商品名字重复");
            }
            shopList.setSrc(addGoodReqModel.getGood().getSrc());
            List<ShopList> selectSrc = shopListMapper.select(shopList);
            if (null != selectSrc && selectSrc.size() >= 1) {
                throw new TradeException("商品图片重复");
            }
            good.setIsDelete("0");
            shopListMapper.insert(good);
            addGoodResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            addGoodResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            addGoodResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            addGoodResModel.setResultMessage(te.getMessage());
            logger.error("商品添加失败", te);
        }
        return addGoodResModel;
    }

    @Override
    public QryMainPageModel qryMainPage() {
        QryMainPageModel qryMainPageModel = new QryMainPageModel();
        try {
            List<ShopList> shopLists = mainPageDAO.qryMainPage();
            qryMainPageModel.setPageInfo(shopLists);
            qryMainPageModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryMainPageModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryMainPageModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryMainPageModel.setResultMessage(te.getMessage());
            logger.error("商品添加失败", te);
        }
        return qryMainPageModel;
    }

    @Override
    public QryGoodListResModel qryGoodList(QryGoodListReqModel qryGoodListReqModel) {
        QryGoodListResModel qryGoodListResModel = new QryGoodListResModel();
        try {
            int pageSize = (0 == qryGoodListReqModel.getPageSize()   ? 15 : qryGoodListReqModel.getPageSize());
            int pageNum = (0 == qryGoodListReqModel.getPageNum() ? 1 : qryGoodListReqModel.getPageNum());
            PageHelper.startPage(pageNum, pageSize);
            List<ShopList> shopLists = qryPageDAO.qryGoodList(qryGoodListReqModel);
            PageInfo<ShopList> shopListPageInfo = new PageInfo<>(shopLists);
            qryGoodListResModel.setPageInfo(shopListPageInfo);
            qryGoodListResModel.setResultCode(ENUM_RESULT_CODE.SUCCESS.getCode());
            qryGoodListResModel.setResultMessage(ENUM_RESULT_CODE.SUCCESS.getDesc());
        } catch (TradeException te) {
            qryGoodListResModel.setResultCode(ENUM_RESULT_CODE.FAIL.getCode());
            qryGoodListResModel.setResultMessage(te.getMessage());
            logger.error("商品查询失败", te);
        }
        return qryGoodListResModel;
    }

}
