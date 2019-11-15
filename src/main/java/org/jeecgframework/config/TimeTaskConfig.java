package org.jeecgframework.config;

import org.jeecgframework.core.timer.DataBaseSchedulerFactoryBean;
import org.jeecgframework.core.timer.DynamicTask;
import org.jeecgframework.web.system.sms.util.JQuartzJobFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeTaskConfig {
    @Bean
    public DataBaseSchedulerFactoryBean schedulerFactory(JQuartzJobFactory jQuartzJobFactory){
        DataBaseSchedulerFactoryBean schedulerFactory = new DataBaseSchedulerFactoryBean();
        schedulerFactory.setJobFactory(jQuartzJobFactory);
        return schedulerFactory;
    }
    @Bean(initMethod = "loadTask")
    public DynamicTask dynamicTask(){
        return new DynamicTask();
    }
}
