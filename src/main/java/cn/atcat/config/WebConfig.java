package cn.atcat.config;

import cn.atcat.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 登录接口、不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/", "/alipay/**", "/recruit/list", "/category/list");
    }
}
