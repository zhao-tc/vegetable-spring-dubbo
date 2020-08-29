package com.ztc.vegetable.manage.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Cart implements Serializable {
    @Id
    @Column(name = "cart_id")
    private Integer cartId;

    /**
     * 订单号
     */
    @Column(name = "ORDERID")
    private String orderid;

    /**
     * 商品id
     */
    @Column(name = "GID")
    private Integer gid;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "p_price")
    private Double pPrice;

    private Integer quantity;

    @Column(name = "p_total")
    private Double pTotal;

    @Column(name = "p_src")
    private String pSrc;

    @Column(name = "IS_DELETE")
    private String isDelete;

    private String status;

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

    private static final long serialVersionUID = 1L;

    /**
     * @return cart_id
     */
    public Integer getCartId() {
        return cartId;
    }

    /**
     * @param cartId
     */
    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    /**
     * 获取订单号
     *
     * @return ORDERID - 订单号
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置订单号
     *
     * @param orderid 订单号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取商品id
     *
     * @return GID - 商品id
     */
    public Integer getGid() {
        return gid;
    }

    /**
     * 设置商品id
     *
     * @param gid 商品id
     */
    public void setGid(Integer gid) {
        this.gid = gid;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return p_name
     */
    public String getpName() {
        return pName;
    }

    /**
     * @param pName
     */
    public void setpName(String pName) {
        this.pName = pName;
    }

    /**
     * @return p_price
     */
    public Double getpPrice() {
        return pPrice;
    }

    /**
     * @param pPrice
     */
    public void setpPrice(Double pPrice) {
        this.pPrice = pPrice;
    }

    /**
     * @return quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * @return p_total
     */
    public Double getpTotal() {
        return pTotal;
    }

    /**
     * @param pTotal
     */
    public void setpTotal(Double pTotal) {
        this.pTotal = pTotal;
    }

    /**
     * @return p_src
     */
    public String getpSrc() {
        return pSrc;
    }

    /**
     * @param pSrc
     */
    public void setpSrc(String pSrc) {
        this.pSrc = pSrc;
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
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cartId=").append(cartId);
        sb.append(", orderid=").append(orderid);
        sb.append(", gid=").append(gid);
        sb.append(", parentId=").append(parentId);
        sb.append(", pName=").append(pName);
        sb.append(", pPrice=").append(pPrice);
        sb.append(", quantity=").append(quantity);
        sb.append(", pTotal=").append(pTotal);
        sb.append(", pSrc=").append(pSrc);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", status=").append(status);
        sb.append(", createdUser=").append(createdUser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}