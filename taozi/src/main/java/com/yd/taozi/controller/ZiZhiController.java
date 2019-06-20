package com.yd.taozi.controller;

import com.yd.taozi.pojo.User;
import com.yd.taozi.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.transaction.xa.XAException;
import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/30.
 */
@Controller
public class ZiZhiController {
    @Autowired
    private UserService userService;
  @RequestMapping("apply")
    public String apply(@Param("leixing")String leixing,Model model,HttpSession session){
        if ("shangye".equals(leixing)){
            session.setAttribute("leixing","商业类型");
        }else if ("geti".equals(leixing)){
            session.setAttribute("leixing","个体工商");
        }else if ("geren".equals(leixing)){
            session.setAttribute("leixing","个体经营");
        }else {
            session.setAttribute("leixing","政府及其非盈利组织");
        }
      System.out.println(leixing);
        return"apply";
  }
  @RequestMapping(value = "one",method = RequestMethod.POST)
    public String applyOne(@Param("leixing")String leixing,
                           @Param("name")String name,
                           @Param("idcard")String idcard,
                           @Param("iphone")String iphone,
                           Model model,HttpSession session){
      if (leixing=="" || name=="" || idcard=="" || iphone==""){
            model.addAttribute("nokong","请输入你的信息");
            return "apply";
        }else{
          try {
              User userByIdcardAndIphone1 = userService.getUserByIdcardAndIphone(idcard, iphone);
          }catch (Exception e){
              e.printStackTrace();
              return "apply";
          }
          User userByIdcardAndIphone = userService.getUserByIdcardAndIphone(idcard, iphone);
          if (userByIdcardAndIphone!=null){
              model.addAttribute("chongfu","请输入正确的信息,以免给你造成不必要的损失");
              return "apply";
          }else {
              session.setAttribute("name",name);
              session.setAttribute("idcard",idcard);
              session.setAttribute("iphone",iphone);
              return "apply-1";
          }
         }
      }
      @RequestMapping(value = "two",method = RequestMethod.POST)
    public String applyTwo(@Param("leixing")String leixing,
                           @Param("name")String name,
                           @Param("idcard")String idcard,
                           @Param("iphone")String iphone,
                           @Param("img")String img,
                           Model model,HttpSession session){
        if (img==""){
            model.addAttribute("muxuan","核心的信息不能为空");
            return "apply-1";
        }
        session.setAttribute("img",img);
        return "apply-2";
      }
      @RequestMapping(value = "three",method = RequestMethod.POST)
      public String applyThree(@Param("leixing")String leixing,
                               @Param("name")String name,
                               @Param("idcard")String idcard,
                               @Param("iphone")String iphone,
                               @Param("img")String img,
                               @Param("email")String email,
                               HttpSession session,
                               Model model){
            if (email==""){
                model.addAttribute("youkong","你的邮箱不能为空");
                return "apply-2";
            }
            session.setAttribute("email",email);
            session.setAttribute("shiming","待审核");
            return "apply-3";
      }
      @RequestMapping(value = "four",method = RequestMethod.POST)
      public String applyFour(@Param("leixing")String leixing,
                              @Param("name")String name,
                              @Param("idcard")String idcard,
                              @Param("iphone")String iphone,
                              @Param("img")String img,
                              @Param("yanzheng")String yangzheng,
                              @Param("uname")String uname,
                              @Param("shiming")String shiming,
                              HttpSession session,
                              Model model){
          System.out.println(shiming);
          User user=new User();
          System.out.println(uname);
         User userByInUname = userService.getUserByInUname(uname);
          user.setUname(uname);
          user.setIdcard(idcard);
          user.setIphone(iphone);
          user.setName(name);
          user.setImg(img);
          user.setLeixing(leixing);
          user.setShiming(shiming);


          //查询
          User user1 = userService.yzSelect(name,idcard,iphone);
          System.out.println(user1);
          System.out.println(user);
          if (yangzheng==""){
              model.addAttribute("yankong","请输入你的验证码");
              return "apply-3";
          }else if (user1==null){
              System.out.println(user1);
              boolean users = userService.insertYanUpdate(user);
              System.out.println(users);
              session.setAttribute("daishenghe","正在审核中");
              return "redirect:member";
          }else {
              model.addAttribute("cunzai","你的身份正在验证中,请耐心等待审核结果");
              return "apply-3";
          }
      }
        @RequestMapping("/auth_cert")
        public String auth_cert(HttpSession session){
            List<User> userByShiName = userService.getUserByShiName();
           session.setAttribute("userByShiName",userByShiName);
           return "auth_cert";
        }
    @RequestMapping("/shenghe")
    public String shenghe(HttpSession session){
        List<User> userByShiName = userService.getUserByShiName();
        session.setAttribute("shengheyemian",userByShiName);
        return "shenghe";
        }
    @RequestMapping(value = "shengheshi",method = RequestMethod.POST)
    public String shengheshi(@Param("uname")String uname,
                            @Param("idcard")String idcard,
                            @Param("iphone")String iphone,
                            @Param("name")String name,
                            @Param("img")String img,
                            @Param("leixing")String leixing,
                            @Param("shiming")String shiming,
                             @Param("queren")String queren,
                             HttpSession session){
        User user=new User();
        user.setUname(uname);
        user.setIdcard(idcard);
        user.setIphone(iphone);
        user.setName(name);
        user.setImg(img);
        user.setLeixing(leixing);
        user.setShiming(shiming);
        System.out.println(queren);
        if ("yes".equals(queren)){
            boolean b = userService.shimingUpdate(user);
            return "redirect:auth_cert";
        }else {
            boolean delshiming = userService.delshiming(user);
            return "redirect:auth_cert";
        }
        }
    }
