package com.example.Controller;


import com.example.Entity.studentInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {
    @Resource
    com.example.Mapper.studentMapper studentMapper;
    @Resource
    com.example.Utils.nikeNameUtils nikeNameUtils;


    @GetMapping("/students")
    public ModelAndView getStudent(){
        ModelAndView students = nikeNameUtils.getUserNikeName("students");
        List<studentInfo> theStudentList = studentMapper.getTheStudentList();
        students.getModel().put("student_list", theStudentList);
        return students;
    }

}
