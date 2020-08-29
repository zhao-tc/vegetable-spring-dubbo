package com.ztc.vegetable.manage.db.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

public class Wish implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    /**
     * 商品id
     */
    @Column(name = "GID")
    private Integer gid;

    @Column(name = "p_name")
    private String pName;

    @Column(name = "p_price")
    private Double pPrice;

    private String src;

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
     * 是否删除标志
     */
    @Column(name = "IS_DELETE")
    private String isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * @return src
     */
    public String getSrc() {
        return src;
    }

    /**
     * @param src
     */
    public void setSrc(String src) {
        this.src = src;
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
     * 获取是否删除标志
     *
     * @return IS_DELETE - 是否删除标志
     */
    public String getIsDelete() {
        return isDelete;
    }

    /**
     * 设置是否删除标志
     *
     * @param isDelete 是否删除标志
     */
    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", parentId=").append(parentId);
        sb.append(", gid=").append(gid);
        sb.append(", pName=").append(pName);
        sb.append(", pPrice=").append(pPrice);
        sb.append(", src=").append(src);
        sb.append(", createdUser=").append(createdUser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", modifiedUser=").append(modifiedUser);
        sb.append(", modifiedDate=").append(modifiedDate);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}