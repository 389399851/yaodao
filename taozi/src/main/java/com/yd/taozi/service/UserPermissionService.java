package com.yd.taozi.service;

import com.yd.taozi.pojo.User;

import java.util.Set;

/**
 * Created by xiaotaozi on 2019/6/13.
 */
public interface UserPermissionService {
    public Set<User> findUserName(String name);
}
