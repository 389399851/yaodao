package com.yd.j1902_gf.controller;

import com.yd.j1902_gf.pojo.User;
import com.yd.j1902_gf.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by xiaotaozi on 2019/5/28.
 */
@Controller
public class UserManagerController {
    @Autowired
    private UserService userService;
    //全查
    @RequestMapping(value = "user",method = RequestMethod.GET)
    public String findAllUser(Model model){
        List<User> all = userService.findAll();
        model.addAttribute("users",all);
        return "user";
    }
    //添加
    @RequestMapping(value = "adduser",method = RequestMethod.POST)
        public String add(HttpServletRequest request, HttpServletResponse response, Model model){
            String uname = request.getParameter("uname");
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String upw = request.getParameter("upw");
        System.out.println(uname);
            User user=new User(uname,email,name);
            User olduser =userService.getUserByName(uname);
            if(uname=="" || email=="" || name==""){
                model.addAttribute("kong","请输入信息");
                return "add";
            }else if (olduser==null){
                boolean useradd = userService.useradd(user);
                model.addAttribute("tianjia","添加成功");
                return "redirect:user";
            } else {
                model.addAttribute("sss","用户名已经存在");
                return "add";
            }
        }
    @RequestMapping(value = "add",method = RequestMethod.GET)
    public String add(){
        return "add";
    }
    //修改
    @RequestMapping(value = "edit",method = RequestMethod.GET)
    public String edit(@RequestParam("id")int id, HttpSession session, Model model){
        session.setAttribute("id",id);
        User user = userService.getUserById(id);
        model.addAttribute("users",user);
        return "edit";
    }
    @RequestMapping(value = "updateUser",method = RequestMethod.POST)
    public String updateUser(@Param("uname")String uname,
                             @Param("name")String name,
                             @Param("email")String email,Model model,HttpSession session){
        User user=new User();
        int id = (int) session.getAttribute("id");
        user.setId(id);
        user.setUname(uname);
        user.setName(name);
        user.setEmail(email);
        boolean update = userService.update(user);
        System.out.println(uname+"=========="+name+"=============="+email);
        if (name=="nidaye"){
            System.out.println(name);
            model.addAttribute("yanyu","请勿输入过激的言语,你可能面临着封号");
            return "edit";
        }else if (update){
            return "redirect:user";
        }
        return "";
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public void deleteUserById(@RequestParam("id")int id,Model model,HttpSession session,HttpServletResponse response) throws IOException {
        System.out.println(id);
        boolean b = userService.deleteUser(id);
        if (b){
            PrintWriter out = response.getWriter();
            out.write("ok");
            out.close();
        }

    }
   @RequestMapping(value = "mohu",method = RequestMethod.GET)
    public String mohu(@Param("chaxuntj")String chaxuntj, Model model){
       List<User> mohuchaxun = userService.mohuchaxun(chaxuntj);
       model.addAttribute("users",mohuchaxun);
           return "user";
    }
    @RequestMapping(value = "pl",method = RequestMethod.POST)
    public void piliangdel(@Param("delitems")String delitems,HttpServletResponse response) throws IOException {
        String[] split = delitems.split(",");
        System.out.println(delitems);
        System.out.println(split);
        for (String ss : split) {
            boolean piliangshanchu = userService.piliangshanchu(Integer.parseInt(ss));

        }
        final PrintWriter writer = response.getWriter();
        writer.write("success");
        writer.close();
    }

}
