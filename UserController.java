package com.example.controller;

import com.example.pojo.User;
import com.example.service.UserService;
import com.example.util.PageInfo;
import com.example.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.ArrayList;
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/toWelcome")
    public String toWelcome()
    {
        return "welcome";
    }

    @RequestMapping("/selectAll")
    public String selectAll(Model model){
        System.out.println("UserController.selectAll");
        List<User> list=userService.selectAll();
        model.addAttribute("list",list);
        return "user_list";


    }
    @RequestMapping("/deleteById")
    public String  deleteById(Integer id){
        userService.deleteById(id);
        return "redirect:/user/selectByPage";
    }

    @RequestMapping("/deleteAll")
    public String  deleteAll(Integer[] ids){

        userService.deleteAll(ids);
        return "redirect:/user/selectByPage";
    }


    @RequestMapping("/toAdd")
    public String toAdd()
    {
        return "user_add";
    }
    @RequestMapping("/add")
    public String add(User user){
        userService.add(user);
        return "redirect:/user/selectByPage";
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Model model)
    {
        User user=userService.selectById(id);
        model.addAttribute("user",user);
        return "user_update";
    }
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user);
        return "redirect:/user/selectByPage";
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user_login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String name, String password,String code, HttpSession session){

        String codeInSession = (String) session.getAttribute("codeInSession");
        if (!codeInSession.equalsIgnoreCase(code)) {
            return Result.error("验证码错误");
        }

        User user=userService.login(name,password);
        if (user!=null){
            if(user.getStatus()==0)
            {
                return Result.error("该用户被禁用");
            }
            session.setAttribute("user",user);
            return Result.ok("登录成功",user);
        }
        else {
            return Result.error("密码错误或用户名错误");
        }
    }
    @RequestMapping("/selectByPage")
    public String selectByPage(@RequestParam(defaultValue = "1") Integer pageNo,
                               @RequestParam(defaultValue = "5") Integer pageSize, Model model){
        System.out.println("UserController.selectByPage");
        PageInfo pageInfo =userService.selectByPage(pageNo,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "user_list";


    }

//    @RequestMapping("/login")
//    public String login(String name, String password, HttpSession session){
//        User user=userService.login(name,password);
//        if (user!=null){
//            session.setAttribute("user",user);
//            return "redirect:/";
//        }
//        else {
//            return "redirect:/user/toLogin";
//        }
//    }

    @RequestMapping("/logout")
    public String logout(HttpSession session)
    {
        session.invalidate();
        return "redirect:/user/toLogin";
    }

    // model.addAttribute("list",list);
//    @RequestMapping("/selectAll")
//    @ResponseBody
//    public List<User> selectAll(Model model){
//        System.out.println("UserController.selectAll");
//        List<User> list=userService.selectAll();
//        model.addAttribute("list",list);
//        return list;
//
//    }


}
