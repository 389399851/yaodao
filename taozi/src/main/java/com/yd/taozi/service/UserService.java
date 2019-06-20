package com.yd.taozi.service;

import com.yd.taozi.pojo.User;

import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/27.
 */
public interface UserService {
//检测用户名
    public User findByUserName(String uname, String upw, String type);
    public User getUserShiMing(String shiming);
//    注册
    public boolean  Regist(User user);
    User getUserByName(String uname);
    //全查
    public List<User> findAll();
    //添加
    public boolean useradd(User user);
    //修改
    User getUserById(int id);
    public boolean update(User user);
    //删除
    public boolean deleteUser(int id);
    //模糊查询
    public List<User> mohuchaxun(String chaxuntj);
    //批量删除
    public boolean piliangshanchu(int id);
    //申请审核
    public User getUserByIdcardAndIphone(String idcard, String iphone);
    public User shiming(User user);
    //验证完成存入数据库
    public User getUserByInUname(String uname);
    public User yzSelect(String name, String idcard, String iphone);
    public boolean insertYanUpdate(User user);
    //实名认证审核
    public List<User> getUserByShiName();
    public boolean shimingUpdate(User user);
    public boolean delshiming(User user);

}
