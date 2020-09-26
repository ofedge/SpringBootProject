package app.lyd.springbootproject.auth.interceptor;

import app.lyd.springbootproject.auth.annotation.AuthIgnore;
import app.lyd.springbootproject.auth.exception.TokenException;
import app.lyd.springbootproject.auth.pojo.BaseUser;
import app.lyd.springbootproject.auth.property.AuthProperty;
import app.lyd.springbootproject.auth.service.BaseAuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    private BaseAuthorizationService<?> authorizationService;

    public AuthorizationInterceptor(BaseAuthorizationService<?> authorizationService) {
        this.authorizationService = authorizationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        AuthIgnore authIgnore;
        HandlerMethod method;
        if (handler instanceof HandlerMethod) {
            method = (HandlerMethod)(handler);
            authIgnore = method.getMethodAnnotation(AuthIgnore.class);
            if (authIgnore != null) {
                return true;
            }

            String token = request.getHeader("Authorization");
            if (token == null) {
                throw new TokenException("Please sign in first!");
            }
            if (!token.startsWith("Bearer ")) {
                throw new TokenException("Invalid token!");
            }

            token = token.replace("Bearer ", "");
            BaseUser baseUser = authorizationService.parseToken(token);
            // put user into request scope and get from the HandlerMethodArgumentResolver(need to be implement)
            request.setAttribute(AuthProperty.USER_REQUEST_KEY, baseUser);
            return true;
        }
        return true;
    }
}
