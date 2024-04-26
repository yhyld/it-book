package com.example.controller;

import com.example.pojo.Borrowed;
import com.example.pojo.User;
import com.example.service.BorrowedService;
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
@RequestMapping("/borrowed")
public class BorrowedController {
    @Autowired
    private BorrowedService borrowedService;
    @RequestMapping("/selectAll")
    public String selectAll(Model model,Integer userId){
        System.out.println("BorrowedController.selectAll");
        List<Borrowed> list=borrowedService.selectAll(userId);
        model.addAttribute("list",list);
        return "student_borrowed_list";


    }
    @RequestMapping("/deleteById")
    public String  deleteById(Integer id){

        borrowedService.deleteById(id);
        return "redirect:/borrowed/selectByPage";
    }

    @RequestMapping("/deleteAll")
    public String  deleteAll(Integer[] ids){

        borrowedService.deleteAll(ids);
        return "redirect:/borrowed/selectByPage";
    }

    @RequestMapping("/sdeleteById")
    public String  sdeleteById(Integer id,Integer userId,Model model){

        borrowedService.deleteById(id);
        List<Borrowed> list=borrowedService.selectAll(userId);
        model.addAttribute("list",list);
        return "student_borrowed_list";
    }

    @RequestMapping("/sdeleteAll")
    public String  sdeleteAll(Integer[] ids,Integer userId,Model model){

        borrowedService.deleteAll(ids);
        List<Borrowed> list=borrowedService.selectAll(userId);
        model.addAttribute("list",list);
        return "student_borrowed_list";
    }


    @RequestMapping("/selectByPage")
    public String selectByPage(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "5") Integer pageSize, Model model){
        System.out.println("BorrowedController.selectByPage");
        PageInfo pageInfo =borrowedService.selectByPage(pageNo,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "borrowed_list";


    }

    @RequestMapping("/add")
    public String add(Borrowed borrowed){
        borrowedService.add(borrowed);
        return "redirect:/book/select";
    }



}
