package com.yd.taozi.controller;
import com.yd.taozi.pojo.User;
import com.yd.taozi.service.UserService;
import com.yd.taozi.utils.ImgCode;
import com.yd.taozi.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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
    //显示无权页面
    @RequestMapping("/unauth")
    public String unauth(){return "unauth";}
    @RequestMapping(value = "/getImg",method = RequestMethod.GET)
    public void getYanzhengMa(HttpServletRequest request,HttpServletResponse response){
        ImgCode imgCode = new ImgCode();
        imgCode.getRandcode(request,response);
    }
    //退出页面（注销用户）
    @RequestMapping("/logout")
    public String tuichu(){
        try {
            Subject subject = SecurityUtils.getSubject();
            //清楚缓存数据
            subject.logout();
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:login";
    }
    //登录处理页面
    @RequestMapping(value = "/denglu",method = RequestMethod.POST)
    public String dengLu(UserVo userVo,HttpSession session) {
        System.out.println(userVo);
        try {
            //从安全管理获取主体对象
            Subject subject = SecurityUtils.getSubject();
            //构建令牌
            UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPassword());
            //登录
            subject.login(token);
            if (subject.isAuthenticated()) {//登录成功后
                User userByInUname = userService.getUserByInUname(userVo.getUsername());
                System.out.println(userByInUname);

                if ("会员".equals(userVo.getType()) && userByInUname.getType().equals("会员")){
                    session.setAttribute("huiyuan",userVo.getUsername());
                    return "member";
                }else if ("管理".equals(userVo.getType()) && userByInUname.getType().equals("管理")){
                    session.setAttribute("guanli",userVo.getUsername());
                    return "main";
                }else {
//                    session.setAttribute("wuquan","你的权限不支持");
                    return "unauth";
                }

            }
        } catch (AuthenticationException e){
            System.out.println("用户名或者密码错误");
            session.setAttribute("cuowu", "用户名或者密码错误");
            return "login";
        }
        return "login";
    }
//    @RequestMapping(value = "/denglu",method = RequestMethod.POST)
//    public String checkLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model){
//        String uname = request.getParameter("uname");
//        String upw = request.getParameter("upw");
//        String type = request.getParameter("type");
//        User byUserName = userService.findByUserName(uname, upw, type);
//        if (byUserName==null){
//            System.out.println("没有此用户");
//            model.addAttribute("kong","你输入的用户不存在");
//            return "login";
//        }else if (type.equals(byUserName.getType())){
//                if (byUserName.getType().equals("管理") && uname.equals(byUserName.getUname()) && upw.equals(byUserName.getUpw())){
//                    System.out.println("进入管理页面");
//                    //model.addAttribute("guanli",uname);
//                    session.setAttribute("guanli",uname);
//                    return "main";
//                } else if (byUserName.getType().equals("会员") && uname.equals(byUserName.getUname()) && upw.equals(byUserName.getUpw())){
//                    System.out.println("进入会员页面");
//                    session.setAttribute("huiyuan",uname);
////                    try {
////                        User userShiMing = userService.getUserShiMing(uname);
////                        String shiming = userShiMing.getShiming();
////                        session.setAttribute("shiming",shiming);
////                    }catch (Exception e){
////                        e.printStackTrace();
////                    }
//                    return "member";
//                }else {
//                    System.out.println("进入错误页面");
//                    model.addAttribute("cuowu","用户名或密码错误");
//                    return "login";
//                }
//            } try {
//            byUserName= userService.findByUserName(uname,upw,type);
////             userShiMing = userService.getUserShiMing(uname);
////             shiming = userShiMing.getShiming();
//            model.addAttribute("buzhichi","你的权限不支持");
//            System.out.println("你的权限不支持");
//            return "login";
//        }catch (Exception e){
//            e.printStackTrace();
//        }return null;
//    }
    //注册页面
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
