package com.yd.test.mapper;

import com.yd.j1902_gf.mapper.UserMapper;
import com.yd.j1902_gf.pojo.User;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/30.
 */

public class mapperTest {
    @Test
    public void test(){
        ClassPathXmlApplicationContext xx = new ClassPathXmlApplicationContext("spring-mybatis.xml");
         UserMapper bean = xx.getBean(UserMapper.class);
         List<User> userByShiName = bean.getUserByShiName();
//         User tangsan = bean.findByUserName("tangsan","1234","会员");
        System.out.println(userByShiName);
//         User asfa = bean.getUserByIdcardAndIphone("807988237", "asfa");
//        System.out.println(asfa);
      /*  List<User> w = bean.mohuchaxun("xiao");
        System.out.println(w);
*/
    }
}
