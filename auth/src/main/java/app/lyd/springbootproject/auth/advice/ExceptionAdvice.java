package app.lyd.springbootproject.auth.advice;

import app.lyd.springbootproject.auth.exception.AuthException;
import app.lyd.springbootproject.auth.exception.TokenException;
import app.lyd.springbootproject.base.web.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionAdvice {

    private Logger logger = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ExceptionHandler(AuthException.class)
    public Result handleAuthException(AuthException authException) {
        return new Result(400, authException.getMessage());
    }

    @ExceptionHandler(TokenException.class)
    public Result handleTokenException(TokenException tokenException) {
        return new Result(401, tokenException.getMessage());
    }

}