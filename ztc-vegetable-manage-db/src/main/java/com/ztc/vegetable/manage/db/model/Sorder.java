package com.ztc.vegetable.manage.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Sorder implements Serializable {
    @Id
    @Column(name = "SID")
    private Integer sid;

    /**
     * 订单号
     */
    private String userid;

    /**
     * 用户名称
     */
    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PHONE")
    private String phone;

    /**
     * 订单价格
     */
    @Column(name = "Pprice")
    private Double pprice;

    @Column(name = "ORDERID")
    private String orderid;

    @Column(name = "IS_DELETE")
    private String isDelete;

    /**
     * 创建人
     */
    @Column(name = "CREATED_USER")
    private String createdUser;

    /**
     * 创建时间
     */
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    /**
     * 修改人
     */
    @Column(name = "MODIFIED_USER")
    private String modifiedUser;

    /**
     * 修改时间
     */
    @Column(name = "MODIFIED_DATE")
    private Date modifiedDate;

    /**
     * 地址
     */
    @Column(name = "ADRESS")
    private String adress;

    private static final long serialVersionUID = 1L;

    /**
     * @return SID
     */
    public Integer getSid() {
        return sid;
    }

    /**
     * @param sid
     */
    public void setSid(Integer sid) {
        this.sid = sid;
    }

    /**
     * 获取订单号
     *
     * @return userid - 订单号
     */
    public String getUserid() {
        return userid;
    }

    /**
     * 设置订单号
     *
     * @param userid 订单号
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * 获取用户名称
     *
     * @return USERNAME - 用户名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名称
     *
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取订单价格
     *
     * @return Pprice - 订单价格
     */
    public Double getPprice() {
        return pprice;
    }

    /**
     * 设置订单价格
     *
     * @param pprice 订单价格
     */
    public void setPprice(Double pprice) {
        this.pprice = pprice;
    }

    /**
     * @return ORDERID
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * @return IS_DELETE
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * @param isDelete
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    /**
     * 获取创建人
     *
     * @return CREATED_USER - 创建人
     */
    public String getCreatedUser() {
        return createdUser;
    }

    /**
     * 设置创建人
     *
     * @param createdUser 创建人
     */
    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    /**
     * 获取创建时间
     *
     * @return CREATED_DATE - 创建时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建时间
     *
     * @param createdDate 创建时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取修改人
     *
     * @return MODIFIED_USER - 修改人
     */
    public String getModifiedUser() {
        return modifiedUser;
    }

    /**
     * 设置修改人
     *
     * @param modifiedUser 修改人
     */
    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    /**
     * 获取修改时间
     *
     * @return MODIFIED_DATE - 修改时间
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifiedDate 修改时间
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * 获取地址
     *
     * @return ADRESS - 地址
     */
    public String getAdress() {
        return adress;
    }

    /**
     * 设置地址
     *
     * @param adress 地址
     */
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sid=").append(sid);
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", phone=").append(phone);
        sb.append(", pprice=").append(pprice);
        sb.append(", orderid=").append(orderid);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", createdUser=").append(createdUser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", adress=").append(adress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}