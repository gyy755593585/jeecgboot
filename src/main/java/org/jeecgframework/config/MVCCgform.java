package org.jeecgframework.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import freemarker.template.TemplateDirectiveModel;
import org.jeecgframework.web.cgform.engine.DBTempletLoader;
import org.jeecgframework.web.cgform.engine.TempletContext;
import org.jeecgframework.web.cgform.engine.tag.DictDataTag;
import org.jeecgframework.web.cgform.engine.tag.MutiLangTag;
import org.jeecgframework.web.cgform.interceptors.CgFormVersionInterceptor;
import org.jeecgframework.web.cgform.service.impl.generate.DBTempletLoaderWord;
import org.jeecgframework.web.cgform.service.impl.generate.TempletContextWord;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ComponentScan(basePackages = {"org.jeecgframework.web.*","com.jeecg"})
public class MVCCgform implements WebMvcConfigurer {
    @Bean
    public freemarker.template.Configuration freemarker(DBTempletLoader templateLoader){
        freemarker.template.Configuration freemarker = new freemarker.template.Configuration();
        freemarker.setTemplateLoader(templateLoader);
        freemarker.setDefaultEncoding("UTF-8");
        freemarker.setNumberFormat("0");
        freemarker.setClassicCompatible(true);
        return  freemarker;
    }
    @Bean
    public TempletContext templetContext(DictDataTag dictDataTag, MutiLangTag mutiLangTag){
        TempletContext templetContext = new TempletContext();
        Map<String, TemplateDirectiveModel> tags = Maps.newHashMap();
        tags.put("DictData",dictDataTag);
        tags.put("mutiLang",mutiLangTag);
        templetContext.setTags(tags);
        return templetContext;
    }
    @Bean
    public freemarker.template.Configuration freemarkerWord(DBTempletLoaderWord templetLoaderWord){
        freemarker.template.Configuration freemarker = new freemarker.template.Configuration();
        freemarker.setTemplateLoader(templetLoaderWord);
        freemarker.setDefaultEncoding("UTF-8");
        freemarker.setNumberFormat("0");
        freemarker.setClassicCompatible(true);
        return  freemarker;
    }
    @Bean
    public TempletContextWord templetContextWord(DictDataTag dictDataTag, MutiLangTag mutiLangTag){
        TempletContextWord templetContext = new TempletContextWord();
        Map<String, TemplateDirectiveModel> tags = Maps.newHashMap();
        tags.put("DictData",dictDataTag);
        tags.put("mutiLang",mutiLangTag);
        templetContext.setTags(tags);
        return templetContext;
    }

    @Bean
    public CgFormVersionInterceptor cgFormVersionInterceptor(){
        CgFormVersionInterceptor cgFormVersionInterceptor = new CgFormVersionInterceptor();
        cgFormVersionInterceptor.setIncludeUrls(Lists.newArrayList(
                "cgformButtonController.do?save",
                "cgformButtonController.do?del",
                "cgformButtonSqlController.do?save",
                "cgformButtonSqlController.do?del",
                "cgformFtlController.do?save",
                "cgformFtlController.do?del",
                "cgformFtlController.do?active",
                "cgformFtlController.do?cancleActive",
                "cgformEnhanceJsController.do?save",
                "cgformEnhanceJsController.do?del",
                "cgFormHeadController.do?save"));
        return  cgFormVersionInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(cgFormVersionInterceptor()).addPathPatterns("/**");
    }
}
