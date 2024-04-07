package com.example.Listener;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

@WebListener
public class MysqlListener implements ServletContextListener {
    //关闭数据库连接
    public void ContextDestroyed(ServletContextEvent sce){
        Enumeration<Driver> dirvers= DriverManager.getDrivers();
        while (dirvers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(dirvers.nextElement());
                AbandonedConnectionCleanupThread.checkedShutdown();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
