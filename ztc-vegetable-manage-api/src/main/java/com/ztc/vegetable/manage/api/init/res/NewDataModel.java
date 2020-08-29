package com.ztc.vegetable.manage.api.init.res;

import com.ztc.vegetable.manage.api.dto.ContentModel;

import java.io.Serializable;
import java.util.List;

public class NewDataModel extends ContentModel implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 日期数组
     */
    private List<String> sevenDay;
    /**
     * 对应日期的数据
     */
    private List<List<Integer>> sevenDate;

    public List<String> getSevenDay() {
        return sevenDay;
    }
    public void setSevenDay(List<String> sevenDay) {
        this.sevenDay = sevenDay;
    }
    public List<List<Integer>> getSevenDate() {
        return sevenDate;
    }
    public void setSevenDate(List<List<Integer>> sevenDate) {
        this.sevenDate = sevenDate;
    }
}
