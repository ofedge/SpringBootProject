package app.lyd.springbootproject.app.consts;

public enum ErrorCode {

    TOKEN_NOT_EXISTS(1000),
    TOKEN_INVALID(1001),
    TOKEN_EXPIRED(1002),

    AUTH_INVALID(1100),
    AUTH_LOCKED(1101),

    PASSWORD_OLD_WRONG(1200),

    SERVER_ERROR(9000);

    private int code;

    ErrorCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
