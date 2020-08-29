package com.ztc.vegetable.manage.restful.comment.bo;

import com.ztc.vegetable.manage.db.model.User;

public class UserContext {
    private static ThreadLocal<User> userHolder = new ThreadLocal<User>();
    
    public static void setUser(User user) {
        userHolder.set(user);
    }
    
    public static User getUser() {
        return userHolder.get();
    }
}
