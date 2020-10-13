package app.lyd.springbootproject.app.web.req;

import javax.validation.constraints.NotBlank;

public class LoginReq {

    @NotBlank(message = "validate.login.username-required")
    private String username;
    @NotBlank(message = "validate.login.password-required")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
