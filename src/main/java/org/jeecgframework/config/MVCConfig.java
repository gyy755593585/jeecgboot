package org.jeecgframework.config;
import org.jeecgframework.core.common.hibernate.dialect.DialectFactoryBean;
import org.jeecgframework.core.interceptors.MyWebBinding;
import org.jeecgframework.web.system.listener.InitListener;
import org.jeecgframework.web.system.service.CacheServiceI;
import org.jeecgframework.web.system.service.impl.EhcacheService;
import org.jeecgframework.web.system.servlet.ImgServerServlet;
import org.jeecgframework.web.system.servlet.RandCodeImageServlet;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.Servlet;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebMvc
//@ImportResource(locations = {"spring-mvc.xml", "spring-mvc-timeTask.xml", "spring-mvc-aop.xml", "spring-mvc-context.xml"})
public class MVCConfig implements WebMvcConfigurer {

    @Bean
    public CacheServiceI cacheServiceI(){
        return new EhcacheService();
    }
/*<!-- 避免IE执行AJAX时,返回JSON出现下载文件 -->*/
    @Bean
    public MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        List<MediaType> supportedMedias = new ArrayList<>();
        supportedMedias.add(MediaType.valueOf("text/html;charset=UTF-8"));
        supportedMedias.add(MediaType.valueOf("text/json;charset=UTF-8"));
        supportedMedias.add(MediaType.valueOf("application/json;charset=UTF-8"));
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMedias);
        return  mappingJackson2HttpMessageConverter;
    }
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter(){
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        List<MediaType> supportedMedias = new ArrayList<>();
        supportedMedias.add(MediaType.valueOf("text/plain;charset=UTF-8"));
        stringHttpMessageConverter.setSupportedMediaTypes(supportedMedias);
        return  stringHttpMessageConverter;
    }

   @Bean
   public RequestMappingHandlerAdapter requestMappingHandlerAdapter(){
       RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
       requestMappingHandlerAdapter.setCacheSeconds(0);
       List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
       messageConverters.add(stringHttpMessageConverter());
       messageConverters.add(mappingJacksonHttpMessageConverter());
       requestMappingHandlerAdapter.setMessageConverters(messageConverters);
       requestMappingHandlerAdapter.setWebBindingInitializer(new MyWebBinding());
       return requestMappingHandlerAdapter;
   }

    @Bean
    @Order(3)
    public InternalResourceViewResolver defaultViewResolver(){
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setViewClass(JstlView.class);
        internalResourceViewResolver.setContentType("text/html");
        internalResourceViewResolver.setPrefix("/webpage/");
        internalResourceViewResolver.setSuffix(".jsp");
        return internalResourceViewResolver;
    }
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
        commonsMultipartResolver.setDefaultEncoding("UTF8");
        commonsMultipartResolver.setMaxUploadSize(104857600L);
        commonsMultipartResolver.setMaxInMemorySize(4096);
        return  commonsMultipartResolver;
    }
    @Bean
    @Order(0)
    public BeanNameViewResolver beanNameViewResolver(){
        return new BeanNameViewResolver();
    }

    @Bean
   public DialectFactoryBean dialect(){
        DialectFactoryBean dialectFactoryBean = new DialectFactoryBean();
        dialectFactoryBean.setDbType("mysql");
        return dialectFactoryBean;
    }
    @Bean
    public ServletRegistrationBean dispatcher(DispatcherServlet dispatcherServlet) {
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        servletServletRegistrationBean.setServlet(dispatcherServlet);
        List<String> urlMappings = new ArrayList<>();
        urlMappings.add("*.do");
        urlMappings.add("/rest/");
        servletServletRegistrationBean.setUrlMappings(urlMappings);
        return servletServletRegistrationBean;
    }
    @Bean
    public ServletRegistrationBean randCodeImageServlet() {
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        RandCodeImageServlet randCodeImageServlet = new RandCodeImageServlet();
        servletServletRegistrationBean.setServlet(randCodeImageServlet);
        List<String> urlMappings = new ArrayList<>();
        urlMappings.add("/randCodeImage");
        servletServletRegistrationBean.setName("randCodeImageServlet");
        servletServletRegistrationBean.setUrlMappings(urlMappings);
        return servletServletRegistrationBean;
    }
    @Bean
    public ServletRegistrationBean imgServerServlet() {
        ServletRegistrationBean<Servlet> servletServletRegistrationBean = new ServletRegistrationBean<>();
        ImgServerServlet imgServerServlet = new ImgServerServlet();
        servletServletRegistrationBean.setServlet(imgServerServlet);
        List<String> urlMappings = new ArrayList<>();
        urlMappings.add("/img/server/*");
        urlMappings.add("/img-online/server/*");
        servletServletRegistrationBean.setName("imgServerServlet");
        servletServletRegistrationBean.setUrlMappings(urlMappings);
        return servletServletRegistrationBean;
    }


    /**
     * request监听器
     */
    @Bean
    public ServletListenerRegistrationBean requestContextListener() {
        ServletListenerRegistrationBean<RequestContextListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        RequestContextListener requestContextListener = new RequestContextListener();
        servletListenerRegistrationBean.setListener(requestContextListener);
        return servletListenerRegistrationBean;
    }
    /**
     * 系统初始化监听器
     */
    @Bean
    @Order(1)
    public ServletListenerRegistrationBean initListener() {
        ServletListenerRegistrationBean<InitListener> servletListenerRegistrationBean = new ServletListenerRegistrationBean<>();
        InitListener initListener = new InitListener();
        servletListenerRegistrationBean.setListener(initListener);
        return servletListenerRegistrationBean;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
