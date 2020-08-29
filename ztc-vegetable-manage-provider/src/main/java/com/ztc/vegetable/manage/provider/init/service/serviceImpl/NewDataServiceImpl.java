package com.ztc.vegetable.manage.provider.init.service.serviceImpl;

import com.ztc.vegetable.manage.api.init.res.NewDataModel;
import com.ztc.vegetable.manage.api.init.service.NewDataService;
import com.ztc.vegetable.manage.provider.comment.util.DateUtil;
import com.ztc.vegetable.manage.provider.init.dao.NewDataDAO;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class NewDataServiceImpl implements NewDataService {
    @Autowired
    private NewDataDAO newDataDAO;

    @Override
    public NewDataModel newDatas() {
        NewDataModel newDataModel = new NewDataModel();
        List<String> data = pastDay();
        //查询新增订单
        List<Map<String, String>> orderMaps = newDataDAO.qryNewData();

        List<Integer> orderData = newData(orderMaps, data);
        //查询新增用户
        List<Map<String, String>> userMaps = newDataDAO.qryUserNewData();
        List<Integer> userData = newData(userMaps, data);
        newDataModel.setSevenDay(data);
        ArrayList<List<Integer>> lists = new ArrayList<>();
        lists.add(userData);
        lists.add(orderData);
        newDataModel.setSevenDate(lists);
        return newDataModel;
    }

    /**
     * 获取日期数组
     *
     * @return
     */
    private List<String> pastDay() {
        ArrayList<String> pastDaysList = new ArrayList<>();
        try {
            //我这里传来的时间是个string类型的，所以要先转为date类型的。
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 6, j = -6; i >= 0; i--, j++) {
                Date date = DateUtil.addDays(new Date(), j);
                String format = sdf.format(date);
                pastDaysList.add(format);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pastDaysList;
    }
    /**
     * 获取对应日期的数据
     */
    private List<Integer>  newData (List<Map<String, String>> maps,List<String> data){
        Map<String, Integer> baseMap = new HashMap<>();
        for (String s : data) {
            baseMap.put(s, 0);
        }
        String key = null;
        String value = null;
        for (Map<String, String> map : maps     //遍历list
        ) {
            Set<String> str = map.keySet();
            for (String s : str) {
                if ("ket".equals(s)) {
                    key = map.get(s);
                }
                if ("value".equals(s)) {
                    value = map.get(s);
                }
            }
            baseMap.put(key, Integer.parseInt(value));
        }
        ArrayList<Integer> newData = new ArrayList<>();
        //获取对应数据
        for (String s : data) {
            newData.add(baseMap.get(s));
        }
        return newData;
    }
}
