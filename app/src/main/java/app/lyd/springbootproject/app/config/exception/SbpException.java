package app.lyd.springbootproject.app.config.exception;

import app.lyd.springbootproject.app.consts.ErrorCode;
import app.lyd.springbootproject.base.exception.BaseException;
import app.lyd.springbootproject.base.pojo.ErrorCodeBuilder;

public class SbpException extends BaseException {

    private ErrorCode errorCode;

    public SbpException(ErrorCode errorCode) {
        super(ErrorCodeBuilder.code(errorCode.getCode()));
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return this.errorCode;
    }
}
