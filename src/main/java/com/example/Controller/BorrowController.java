package com.example.Controller;


import com.example.Entity.bookInfo;
import com.example.Entity.studentInfo;
import com.example.Mapper.borrowMapper;
import com.example.Mapper.studentMapper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
public class BorrowController {
    //学生列表
    @Resource
    studentMapper studentMapper;
    //借阅接口操作
    @Resource
    borrowMapper borrowMapper;

    @PostMapping("/add-borrow")
    public String addBorrow(@RequestParam("sid")Integer sid,
                            @RequestParam("bid")Integer bid,
                            HttpServletResponse httpServletResponse, Model model) throws IOException {
        httpServletResponse.setContentType("text/html;charset=UTF-8");
        int i = borrowMapper.insertBorrowInfo(sid, bid);
        if (i!=0){
            httpServletResponse.getWriter().write("<script>window.alert('借阅成功！')</script>");
        }else {
            httpServletResponse.getWriter().write("<script>window.alert('借阅失败！')</script>");
        }
        List<studentInfo> theStudentList = studentMapper.getTheStudentList();
        List<bookInfo> theBookInfoList= borrowMapper.getTheLatest();
        model.addAttribute("student_list", theStudentList);
        //添加借书的列表操作，将借书的列表从集合参数中进行删除操作，关于获取的相关理解操作
        model.addAttribute("book_list", theBookInfoList);
        //图书列表
        return "add-borrow";
    }



    @GetMapping("/return-book")
    public String deleteTheBorrowInfo(@RequestParam("id")Integer id){
        borrowMapper.deleteTheBorrowInfo(id);
        return "redirect:userPage";
    }
}
