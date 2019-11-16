package org.jeecgframework.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;
import org.hibernate.SessionFactory;
import org.jeecgframework.core.aop.HiberAspect;
import org.jeecgframework.core.extend.datasource.DataSourceType;
import org.jeecgframework.core.extend.datasource.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:/dbconfig.properties")
public class DaoConfig {
    @Value("${jdbc.driver}")
    private String driverClassName;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernate_hbm2ddl_auto;
    @Value("${hibernate.dialect}")
    private String hibernate_dialect;
    @Bean(name = "dataSource_jeecg")
    public DataSource dataSourceJeecg() {
        /**
         * <!-- 最小空闲连接数 --> <property name="minIdle" value="10" /> <!--
         * 配置连接池初始化大小 --> <property name="initialSize" value="20" /> <!-- 最大连接数
         * --> <property name="maxActive" value="20" /> <!-- 获取连接等待超时的时间，单位：毫秒
         * --> <property name="maxWait" value="2000" />
         */
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(this.driverClassName);
        dataSource.setUrl(this.jdbcUrl);
        dataSource.setUsername(this.username);
        dataSource.setPassword(this.password);
        dataSource.setInitialSize(20);
        dataSource.setMinIdle(10);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(2000);
        return dataSource;
    }
    @Bean
    public DataSource dataSource() {
        DynamicDataSource dataSource = new DynamicDataSource();
        Map<DataSourceType,Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.dataSource_jeecg,dataSourceJeecg());
        dataSource.setTargetDataSources(targetDataSources);
        dataSource.setDefaultTargetDataSource(dataSourceJeecg());
        return dataSource;
    }

   @Autowired
   private HiberAspect hiberAspect;

   @Bean(name = "sessionFactory")
   public LocalSessionFactoryBean sessionFactory() {
       LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
       sessionFactoryBean.setDataSource(dataSource());
       sessionFactoryBean.setEntityInterceptor(hiberAspect);
       Properties hibernateProperties = new Properties();
       hibernateProperties.setProperty("hibernate.hbm2ddl.auto",hibernate_hbm2ddl_auto);
       hibernateProperties.setProperty("hibernate.dialect",hibernate_dialect);
       hibernateProperties.setProperty("hibernate.show_sql","false");
       sessionFactoryBean.setHibernateProperties(hibernateProperties);
       sessionFactoryBean.setPackagesToScan("org.jeecgframework.web.system.pojo.*",
               "org.jeecgframework.web.cgform.entity.*",
               "org.jeecgframework.web.cgreport.entity.*",
               "org.jeecgframework.web.cgdynamgraph.entity.*",
               "org.jeecgframework.web.graphreport.entity.*",
               "org.jeecgframework.web.system.sms.*",
               "org.jeecgframework.web.black.*",
               "org.jeecgframework.web.superquery.*");
       return sessionFactoryBean;
   }
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(this.sessionFactory().getObject());
        return transactionManager;
    }
    @Bean
    public JdbcTemplate jdbcTemplate(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }
    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
        NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
        return jdbcTemplate;
    }
    @Bean
    public Validator validator(){
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        return localValidatorFactoryBean;
    }
    @Bean
    public FilterRegistrationBean openSessionInView(){
        FilterRegistrationBean<OpenSessionInViewFilter> openSessionInView = new FilterRegistrationBean<>();
        openSessionInView.setFilter(new OpenSessionInViewFilter());
        openSessionInView.setUrlPatterns(Lists.newArrayList("/*"));
        return openSessionInView;
    }
}
