package com.yd.taozi.service.impl;

import com.yd.taozi.mapper.UserMapper;
import com.yd.taozi.pojo.User;
import com.yd.taozi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/27.
 */
@Service
public class UserServiceImpl implements UserService{
    @Resource
    private UserMapper userMapper;


    @Override
    public User findByUserName(String uname,String upw,String type) {
        User byUserName = userMapper.findByUserName(uname,upw,type);
        return byUserName;
    }

    @Override
    public User getUserShiMing(String uname) {
        User userShiMing = userMapper.getUserShiMing(uname);
        return userShiMing;
    }

    @Override
    public boolean  Regist(User user) {
        boolean b = userMapper.registerByUsernameAndPassword(user);
        return b;
    }

    @Override
    public User getUserByName(String uname) {
        User name = userMapper.getUserByName(uname);
        return name;

    }

    @Override
    public List<User> findAll() {
        List<User> all = userMapper.findAll();
        return all;
    }

    @Override
    public boolean useradd(User user) {
        boolean useradd = userMapper.useradd(user);
        return useradd;
    }

    @Override
    public User getUserById(int id) {
        User userById = userMapper.getUserById(id);
        return userById;
    }

    @Override
    public boolean update(User user) {
        boolean update = userMapper.update(user);
        return update;
    }

    @Override
    public boolean deleteUser(int id) {
        boolean b = userMapper.deleteUser(id);
        return b;
    }

    @Override
    public List<User> mohuchaxun(String chaxuntj) {
         List<User> mohuchaxun = userMapper.mohuchaxun(chaxuntj);
        return mohuchaxun;
    }

    @Override
    public boolean piliangshanchu(int id) {
        boolean piliangshanchu = userMapper.piliangshanchu(id);
        return piliangshanchu;
    }

    @Override
    public User getUserByIdcardAndIphone(String idcard, String iphone) {
        User userByIdcardAndIphone = userMapper.getUserByIdcardAndIphone(idcard, iphone);
        return userByIdcardAndIphone;
    }

    @Override
    public User shiming(User user) {
        User shiming = userMapper.shiming(user);
        return shiming;
    }

    @Override
    public User yzSelect(String name,  String idcard, String iphone) {
        User user = userMapper.yzSelect(name, idcard, iphone);
        return user;
    }
    @Override
    public User getUserByInUname(String uname) {
        User userByInUname = userMapper.getUserByInUname(uname);
        return userByInUname;
    }

    @Override
    public boolean insertYanUpdate(User user) {
        boolean b = userMapper.insertYanUpdate(user);
        return b;
    }

    @Override
    public List<User> getUserByShiName() {
         List<User> userByShiName = userMapper.getUserByShiName();
         return userByShiName;
    }

    @Override
    public boolean shimingUpdate(User user) {
        boolean b = userMapper.shimingUpdate(user);
        return b;
    }

    @Override
    public boolean delshiming(User user) {
        boolean delshiming = userMapper.delshiming(user);
        return delshiming;
    }


}
