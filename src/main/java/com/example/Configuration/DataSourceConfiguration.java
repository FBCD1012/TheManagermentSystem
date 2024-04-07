package com.example.Configuration;


import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


//构建数据源
@Configuration
public class DataSourceConfiguration {

    @Bean
    public DataSource getDataSource() {
        return new PooledDataSource(
                "com.mysql.cj.jdbc.Driver"
                ,"jdbc:mysql://localhost:3306/themanagermentsystem"
                ,"root"
                ,"FBCD"
        );
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean;
    }
}
