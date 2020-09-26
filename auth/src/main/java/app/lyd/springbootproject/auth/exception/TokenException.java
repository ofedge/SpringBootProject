package app.lyd.springbootproject.auth.exception;

public class TokenException extends RuntimeException {

    public TokenException(String msg) {
        super(msg);
    }

    public TokenException(Exception e) {
        super(e);
    }
}
