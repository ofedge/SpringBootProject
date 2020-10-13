package app.lyd.springbootproject.base.pojo;

public class ErrorCodeBuilder {
    private int code;

    private ErrorCodeBuilder(int code) {
        this.code = code;
    }

    public static ErrorCodeBuilder code(int code) {
        return new ErrorCodeBuilder(code);
    }

    public int getCode() {
        return code;
    }
}
