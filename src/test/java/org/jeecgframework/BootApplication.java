package org.jeecgframework;

import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.core.util.ApplicationContextUtil;
import org.jeecgframework.web.system.service.impl.EhcacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Objects;

@ServletComponentScan
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BootApplication  {

    @Test
    public void testApplication(){
        EhcacheService bean = ApplicationContextUtil.getContext().getBean(EhcacheService.class);
        if(Objects.isNull(bean)){
            log.info("---------{}","空");
        }else {
            log.info("*************************");
        }
    }

}
