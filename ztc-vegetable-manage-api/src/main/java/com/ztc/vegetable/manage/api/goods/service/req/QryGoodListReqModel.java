package com.ztc.vegetable.manage.api.goods.service.req;

import java.io.Serializable;
import java.util.Date;

public class QryGoodListReqModel  implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 开始日期
     */
    private Date start ;
    /**
     * 结束日期
     */
    private Date end ;

    /**
     * 名字
     */
    private String name;

    /**
     * 种类
     */
    private String flg;
    /**
     * 当前页
     */
    private int  pageNum;
    /**
     *每页条数
     */
    private int  pageSize;

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlg() {
        return flg;
    }

    public void setFlg(String flg) {
        this.flg = flg;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
