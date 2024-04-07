package com.example.Controller;


import com.example.Mapper.bookMapper;
import com.example.Utils.nikeNameUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import netscape.javascript.JSObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class BookController {
    @Resource
    bookMapper bookMapper;

    @Resource
    nikeNameUtils nikeNameUtils;

    //此处进行书籍信息管理操作
    @GetMapping("/add-books")
    public ModelAndView addBooks(){
        return nikeNameUtils.getUserNikeName("add-book");
    }

    //如何实现添加用户信息，然后将用户信息添加成功之后的信息显示到相关的
    @PostMapping("/add-bookInfo")
    public String insertIntoBookInfo(@RequestParam("title")String bookName, @RequestParam("desc")String bookDescription
                                        , @RequestParam("price")String bookPrice, HttpServletResponse httpServletResponse
                                     ) throws IOException {
        int i = bookMapper.InsertIntoBookInfo(bookName, bookDescription, bookPrice);
        if (i > 0) {
            httpServletResponse.setContentType("text/html;charset=UTF-8");
            httpServletResponse.getWriter().write("<script>window.alert('书籍添加成功')</script>");
        }
        return  "add-book";
    }
    @GetMapping("/delete-book")
    public String deleteBookInfo(@RequestParam("bookId")Integer bookId){
        bookMapper.deleteBookInfo(bookId);
        return "redirect:books";
    }
}
