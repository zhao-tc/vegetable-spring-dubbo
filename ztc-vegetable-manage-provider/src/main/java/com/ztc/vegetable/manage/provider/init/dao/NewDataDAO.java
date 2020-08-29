package com.ztc.vegetable.manage.provider.init.dao;

import java.util.List;
import java.util.Map;

public interface NewDataDAO {
  /**
   *获取新增订单数量
   * @return
   */
  List<Map<String ,String>> qryNewData();
  /**
   * 获取新增用户数量
   */
  List<Map<String ,String>> qryUserNewData();
}
