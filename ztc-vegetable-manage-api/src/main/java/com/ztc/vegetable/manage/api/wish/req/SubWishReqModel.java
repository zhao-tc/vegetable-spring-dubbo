package com.ztc.vegetable.manage.api.wish.req;

import com.ztc.vegetable.manage.db.model.Wish;

import java.io.Serializable;

public class SubWishReqModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Wish wish;

    private Integer Uid;

    public Wish getWish() {
        return wish;
    }

    public void setWish(Wish wish) {
        this.wish = wish;
    }

    public Integer getUid() {
        return Uid;
    }

    public void setUid(Integer uid) {
        Uid = uid;
    }

}
