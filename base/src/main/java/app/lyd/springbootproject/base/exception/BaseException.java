package app.lyd.springbootproject.base.exception;

import app.lyd.springbootproject.base.pojo.ErrorCodeBuilder;

public class BaseException extends RuntimeException {
    private final ErrorCodeBuilder errorCodeBuilder;

    public BaseException(ErrorCodeBuilder errorCodeBuilder) {
        this.errorCodeBuilder = errorCodeBuilder;
    }

    public int getCode() {
        return errorCodeBuilder.getCode();
    }
}
