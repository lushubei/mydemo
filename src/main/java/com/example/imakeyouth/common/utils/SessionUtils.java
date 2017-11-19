package com.example.imakeyouth.common.utils;


import com.example.imakeyouth.model.User;

public class SessionUtils {
    private static ThreadLocal<User> localUser = new ThreadLocal();

    public static void setUser(User user) {
        localUser.set(user);
    }

    public static User getUser() {
        return localUser.get();
    }

}
