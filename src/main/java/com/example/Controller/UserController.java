package com.example.Controller;


import com.example.Entity.bookInfo;
import com.example.Entity.borrowInfo;
import com.example.Entity.studentInfo;
import com.example.Mapper.bookMapper;
import com.example.Mapper.borrowMapper;
import com.example.Mapper.studentMapper;
import com.example.Utils.nikeNameUtils;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
public class UserController {

    @Resource
    studentMapper studentMapper;
    @Resource
    bookMapper bookMapper;

    @Resource
    borrowMapper borrowMapper;

    @Resource
    nikeNameUtils nikeNameUtils;

    //为什么必须要有根目录，减少一次请求操作，并且执行默认的理解操作
    @GetMapping("/")
    public String rootIndex(){
        return "Login";
    }

    @GetMapping("/userPage")
    public ModelAndView getUserPage(){
        ModelAndView content = nikeNameUtils.getUserNikeName("Content");
        List<borrowInfo> borrowInfos = borrowMapper.getBorrowInfos();
        content.addObject("borrowList",borrowInfos);
        content.addObject("student_count",studentMapper.getTheStudentCountInt());
        return content;
    }
    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView mv=new ModelAndView("Login");
        mv.getModel().put("failure", false);
        return mv;
    }
    //直接进行登陆失败操作，内嵌两个页面即可
    @GetMapping("/failure")
    public ModelAndView failureLoginPage() {
        ModelAndView mv=new ModelAndView("Login");
        mv.getModel().put("failure", true);
        return mv;
    }
    @GetMapping("/books")
    public ModelAndView getTheBooks(){
        ModelAndView books = nikeNameUtils.getUserNikeName("books");

        //专门理解此处的逻辑操作
        List<bookInfo> theBookInfos = bookMapper.getTheBookInfos();
        Set<Integer> set=new HashSet<>();
        borrowMapper.getTheLatest().forEach(borrow->set.add(borrow.getBookId()));
        Map<bookInfo,Boolean> map=new LinkedHashMap<>();
        theBookInfos.forEach(bookInfo -> map.put(bookInfo, !set.contains(bookInfo.getBookId())));


        books.getModel().put("book_list", map.keySet());
        books.getModel().put("book_list_status", new ArrayList<>(map.values()));
        return books;
    }
    @GetMapping("/borrow")
    public String addBorrow(Model model){
        List<studentInfo> studentInfoList=studentMapper.getTheStudentList();
        List<bookInfo> bookInfoList=bookMapper.getTheBookInfos();
        model.addAttribute("book_list", bookInfoList);
        model.addAttribute("student_list", studentInfoList);
        return "add-borrow";
    }
}
