package com.example.controller;

import com.example.pojo.Book;
import com.example.pojo.BookType;
import com.example.service.BookService;
import com.example.service.BookTypeService;
import com.example.util.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private BookTypeService bookTypeService;
    @RequestMapping("/selectAll")
    public String selectAll(Model model){
        System.out.println("BookController.selectAll");
        List<Book> list=bookService.selectAll();
        model.addAttribute("list",list);
        return "book_list";


    }
    @RequestMapping("/deleteById")
    public String  deleteById(Integer id){

        bookService.deleteById(id);
        return "redirect:/book/selectByPage";
    }

    @RequestMapping("/deleteAll")
    public String  deleteAll(Integer[] ids){

        bookService.deleteAll(ids);
        return "redirect:/book/selectByPage";
    }


    @RequestMapping("/toAdd")
    public String toAdd(Model model)
    {
        List<BookType> list= bookTypeService.selectAll();
        model.addAttribute("list", list);
        return "book_add";
    }
    @RequestMapping("/add")
    public String add(Book book){
        bookService.add(book);
        return "redirect:/book/selectByPage";
    }
    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Model model)
    {
        List<BookType> list= bookTypeService.selectAll();
        model.addAttribute("list", list);
        Book book=bookService.selectById(id);
        model.addAttribute("book",book);
        return "book_update";
    }
    @RequestMapping("/update")
    public String update(Book book){
        bookService.update(book);
        return "redirect:/book/selectByPage";
    }

    @RequestMapping("/selectByPage")
    public String selectByPage(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        System.out.println("BookController.selectByPage");
        PageInfo pageInfo =bookService.selectByPage(pageNo,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "book_list";


    }

    @RequestMapping("/select")
    public String select(@RequestParam(defaultValue = "1") Integer pageNo, @RequestParam(defaultValue = "10") Integer pageSize, Model model){
        System.out.println("BookController.selectByPage");
        PageInfo pageInfo =bookService.selectByPage(pageNo,pageSize);
        model.addAttribute("pageInfo",pageInfo);
        return "student_book_list";
    }

//    @RequestMapping("/login")
//    public String login(String name, String password, HttpSession session){
//        Book book=bookService.login(name,password);
//        if (book!=null){
//            session.setAttribute("book",book);
//            return "redirect:/";
//        }
//        else {
//            return "redirect:/book/toLogin";
//        }
//    }


    // model.addAttribute("list",list);
//    @RequestMapping("/selectAll")
//    @ResponseBody
//    public List<Book> selectAll(Model model){
//        System.out.println("BookController.selectAll");
//        List<Book> list=bookService.selectAll();
//        model.addAttribute("list",list);
//        return list;
//
//    }


}
