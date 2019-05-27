package com.syd.model;

import org.springframework.stereotype.Component;


@Component
public class HostHolder {
    private static ThreadLocal<Object> users = new ThreadLocal<Object>();

    public Object getUser() {
        return users.get();
    }

    public void setUser(Object user) {
        users.set(user);
    }

    public void clear() {
        users.remove();;
    }
}
