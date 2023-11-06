package com.myhd.util;

public class UserHolder {
    private static final ThreadLocal<Object> tl = new ThreadLocal<>();

    public static void saveUser(Object object){
        tl.set(object);
    }

    public static <T> T getUser(Class<T> clazz){
        return (T) tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
