package app.lyd.springbootproject.app.config.exception;

import app.lyd.springbootproject.app.consts.ErrorCode;
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

    @ExceptionHandler(SbpException.class)
    public Result handlerSbpException(SbpException sbpException) {
        return new Result(sbpException.getErrorCode().getCode());
    }

    @ExceptionHandler(Throwable.class)
    public Result handleThrowable(Throwable throwable) {
        logger.error(throwable.getMessage(), throwable);
        return new Result(ErrorCode.SERVER_ERROR.getCode());
    }
}
