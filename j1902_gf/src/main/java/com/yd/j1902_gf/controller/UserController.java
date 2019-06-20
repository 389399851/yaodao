package com.yd.j1902_gf.controller;

import com.yd.j1902_gf.pojo.User;
import com.yd.j1902_gf.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by xiaotaozi on 2019/5/27.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/")
    public String index(){return "index";}
    @RequestMapping("index")
    public String indexAll(){return "index";}
    @RequestMapping("login")
    public String login(){return "login";}
    @RequestMapping("reg")
    public String reg(){return "reg";}
    @RequestMapping("accttype")
    public String accttype(){return "accttype";}
    @RequestMapping("member")
    public String member(){return "member";}
    @RequestMapping("minecrowdfunding")
    public String minecrowdfunding(){return "minecrowdfunding";}
    @RequestMapping("start")
    public String start(){return "start";}
    @RequestMapping("start-step-1")
    public String startStep1(){return "start-step-1";}
    @RequestMapping("start-step-2")
    public String startStep2(){return "start-step-2";}
    @RequestMapping("start-step-3")
    public String startStep3(){return "start-step-3";}
    @RequestMapping("startstep-4")
    public String startStep4(){return "startstep-4";}
//    @RequestMapping("shenghe")
//    public String shenghe(){return "shenghe";}
//    @RequestMapping("auth_cert")
//    public String auth_cert(){return "auth_cert";}

    @RequestMapping(value = "denglu",method = RequestMethod.POST)
    public String checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
        String uname = request.getParameter("uname");
        String upw = request.getParameter("upw");
        String type = request.getParameter("type");
        User byUserName = userService.findByUserName(uname, upw, type);
        if (byUserName==null){
            System.out.println("没有此用户");
            model.addAttribute("kong","你输入的用户不存在");
            return "login";
        }else if (type.equals(byUserName.getType())){
                if (byUserName.getType().equals("管理") && uname.equals(byUserName.getUname()) && upw.equals(byUserName.getUpw())){
                    System.out.println("进入管理页面");
                    //model.addAttribute("guanli",uname);
                    session.setAttribute("guanli",uname);
                    return "main";
                } else if (byUserName.getType().equals("会员") && uname.equals(byUserName.getUname()) && upw.equals(byUserName.getUpw())){
                    System.out.println("进入会员页面");
                    session.setAttribute("huiyuan",uname);
//                    try {
//                        User userShiMing = userService.getUserShiMing(uname);
//                        String shiming = userShiMing.getShiming();
//                        session.setAttribute("shiming",shiming);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
                    return "member";
                }else {
                    System.out.println("进入错误页面");
                    model.addAttribute("cuowu","用户名或密码错误");
                    return "login";
                }
            } try {
            byUserName= userService.findByUserName(uname,upw,type);
//             userShiMing = userService.getUserShiMing(uname);
//             shiming = userShiMing.getShiming();
            model.addAttribute("buzhichi","你的权限不支持");
            System.out.println("你的权限不支持");
            return "login";
        }catch (Exception e){
            e.printStackTrace();
        }return null;
    }
    @RequestMapping(value = "zhuce",method = RequestMethod.POST)
    public String zhuce(HttpServletRequest request,HttpServletResponse response,Model model){
        String uname = request.getParameter("uname");
        String email = request.getParameter("email");
        String upw = request.getParameter("upw");
        String type = request.getParameter("type");
        System.out.println(uname);
        User user=new User(uname,upw,email,type);
        User olduser =userService.getUserByName(uname);
        if(uname=="" || upw=="" || email==""){
            model.addAttribute("kong","请输入注册信息");
            return "reg";
        }else if (olduser==null){
            boolean regist = userService.Regist(user);
            return "login";

        } else {
            model.addAttribute("sss","用户名已经存在");
            return "reg";
        }
    }
}
