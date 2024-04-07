package com.example.Utils;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Component
public class nikeNameUtils {
    public ModelAndView getUserNikeName(String pageName){
        ModelAndView modelAndView=new ModelAndView(pageName);
        SecurityContext securityContextHolder= SecurityContextHolder.getContext();
        modelAndView.addObject("nickname", securityContextHolder.getAuthentication().getName());
        return modelAndView;
    }
}
