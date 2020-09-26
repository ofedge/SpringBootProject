package app.lyd.springbootproject.auth.config;

import app.lyd.springbootproject.auth.interceptor.AuthorizationInterceptor;
import app.lyd.springbootproject.auth.service.BaseAuthorizationService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnBean(BaseAuthorizationService.class)
public class AuthAutoConfig implements WebMvcConfigurer {

    private final BaseAuthorizationService<?> authorizationService;

    public AuthAutoConfig(BaseAuthorizationService<?> authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthorizationInterceptor(authorizationService)).addPathPatterns("/api/**");
    }
}
