package com.yd.taozi.mapper;

import com.yd.taozi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/27.
 */
@Mapper
public interface UserMapper {
    //登录
    public User findByUserName(@Param("uname") String uname, @Param("upw") String upw, @Param("type") String type);
    public User getUserShiMing(String shiming);
    //注册
    User getUserByName(String uname);
    public boolean registerByUsernameAndPassword(User user);
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
    public User getUserByIdcardAndIphone(@Param("idcard") String idcard, @Param("iphone") String iphone);
    public User shiming(User user);
    //验证完成添加到数据库
    public User getUserByInUname(@Param("uname") String uname);
    public User yzSelect(@Param("name") String name, @Param("idcard") String idcard, @Param("iphone") String iphone);
    public boolean insertYanUpdate(User user);
    //实名认证
    public List<User> getUserByShiName();
    public boolean shimingUpdate(User user);
    public boolean delshiming(User user);

}


