package com.hd123.framework.es.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Random;

/**
 * @author liweihua
 * 2017.12.14
 */
@Configuration
public class DruidConfig {
    private static final Logger log = LoggerFactory.getLogger(DruidConfig.class);
    @Value("${eslog.datasource.url}")
    private String dbUrl;
    @Value("${eslog.datasource.username}")
    private String username;
    @Value("${eslog.datasource.password}")
    private String password;
    @Value("${eslog.datasource.druid.username:admin}")
    private String druidUsername;
    @Value("${eslog.datasource.druid.password:hd123}")
    private String druidPassword;
    @Value("${eslog.datasource.driver-class-name:com.mysql.jdbc.Driver}")
    private String driverClassName;
    @Value("${eslog.datasource.initialSize:10}")
    private int initialSize;
    @Value("${eslog.datasource.minIdle:10}")
    private int minIdle;
    @Value("${eslog.datasource.maxActive:1000}")
    private int maxActive;
    @Value("${eslog.datasource.maxWait:-1}")
    private int maxWait;
    @Value("${eslog.datasource.timeBetweenEvictionRunsMillis:60000}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${eslog.datasource.minEvictableIdleTimeMillis:1800000}")
    private int minEvictableIdleTimeMillis;
    @Value("${eslog.datasource.validationQuery:SELECT 1}")
    private String validationQuery;
    @Value("${eslog.datasource.testWhileIdle:true}")
    private boolean testWhileIdle;
    @Value("${eslog.datasource.testOnBorrow:false}")
    private boolean testOnBorrow;
    @Value("${eslog.datasource.testOnReturn:false}")
    private boolean testOnReturn;
    @Value("${eslog.datasource.poolPreparedStatements:true}")
    private boolean poolPreparedStatements;
    @Value("${eslog.datasource.filters:stat,log4j}")
    private String filters;
    @Value("${eslog.datasource.logSlowSql:true}")
    private String logSlowSql;
    @Value("${server.workerId:1}")
    private Long workerId;

    public DruidConfig() {
    }

    @Bean
    public ServletRegistrationBean druidServlet() {
        ServletRegistrationBean reg = new ServletRegistrationBean();
        reg.setServlet(new StatViewServlet());
        reg.addUrlMappings(new String[]{"/druid/*"});
        reg.addInitParameter("loginUsername", this.druidUsername);
        reg.addInitParameter("loginPassword", this.druidPassword);
        reg.addInitParameter("logSlowSql", this.logSlowSql);
        return reg;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns(new String[]{"/*"});
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        return filterRegistrationBean;
    }

    @Bean
    @Primary
    public DataSource druidDataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);
        datasource.setDriverClassName(this.driverClassName);
        datasource.setInitialSize(this.initialSize);
        datasource.setMinIdle(this.minIdle);
        datasource.setMaxActive(this.maxActive);
        datasource.setMaxWait((long)this.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis((long)this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis((long)this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);
        datasource.setPoolPreparedStatements(this.poolPreparedStatements);

        try {
            datasource.setFilters(this.filters);
        } catch (SQLException var3) {
            log.error("druid configuration initialization filter", var3);
        }

        return datasource;
    }

    @Bean
    @Primary
    public IdGenerator getIdCenerator() {
        if(this.workerId.longValue() < 1L) {
            Random commonSelfIdGenerator = new Random();
            int id = commonSelfIdGenerator.nextInt(1023);
            this.workerId = Long.valueOf((long)id);
        }

        CommonSelfIdGenerator.setWorkerId(this.workerId);
        CommonSelfIdGenerator commonSelfIdGenerator1 = new CommonSelfIdGenerator();
        return commonSelfIdGenerator1;
    }
}

