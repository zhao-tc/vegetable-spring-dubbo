package com.ztc.vegetable.manage.api.user.service.req;

import java.io.Serializable;
import java.util.Date;

public class QryAUserReqModel implements Serializable {
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
     * 用户id
     */
    private Integer userid;
    /**
     * 用户名
     */
    private String userName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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
