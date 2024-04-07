package com.example.Initializer;

import com.example.Configuration.DataSourceConfiguration;
import com.example.Configuration.MvcConfiguration;
import com.example.Configuration.SecurityConfiguration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



//创建注解操作
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                DataSourceConfiguration.class,
                MvcConfiguration.class,
                SecurityConfiguration.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
