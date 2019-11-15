package org.jeecgframework.config;

import com.google.common.collect.Lists;
import org.jeecgframework.core.interceptors.AuthInterceptor;
import org.jeecgframework.core.interceptors.EncodingInterceptor;
import org.jeecgframework.jwt.aop.RestAuthTokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Bean
    public EncodingInterceptor encodingInterceptor(){
        return new EncodingInterceptor();
    }
    @Bean
    public RestAuthTokenInterceptor restAuthTokenInterceptor(){
        RestAuthTokenInterceptor restAuthTokenInterceptor = new RestAuthTokenInterceptor();
        restAuthTokenInterceptor.setExcludeContainUrls(Lists.newArrayList("/rest/v2/api-docs"));
        restAuthTokenInterceptor.setExcludeUrls(Lists.newArrayList("/rest/v2/api-docs"));
        return restAuthTokenInterceptor;
    }

    @Bean
    public AuthInterceptor authInterceptor(){
        AuthInterceptor authInterceptor = new AuthInterceptor();
        authInterceptor.setExcludeUrls(Lists.newArrayList("/rest/v2/api-docs",
                "loginController.do?goPwdInit",
                "loginController.do?pwdInit",
                "loginController.do?login",
                "loginController.do?logout",
                "loginController.do?changeDefaultOrg",
                "loginController.do?login2",
                "loginController.do?login3",
                "loginController.do?checkuser",
                "loginController.do?checkuser",
                "systemController.do?saveFiles",
                "loginController.do?goResetPwd",
                "loginController.do?resetPwd",
                "loginController.do?goResetPwdMail",
                "loginController.do?sendResetPwdMail",
                "userController.do?userOrgSelect",
                "cgDynamGraphController.do?design",
                "cgDynamGraphController.do?datagrid",
                "webpage/common/functionIconStyleList.jsp",
                "chat/imController/showOrDownByurl.do",
                "timeTaskController.do?remoteTask",
                "rest/v2/api-docs"));
        authInterceptor.setExcludeContainUrls(Lists.newArrayList("toLogin.do",
                "rest/",
                "plug-in/*"));
        return authInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(encodingInterceptor()).addPathPatterns("/*.do");
        registry.addInterceptor(restAuthTokenInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authInterceptor()).addPathPatterns("/*.do");
    }
}
