package app.lyd.springbootproject.app.web.req;

import javax.validation.constraints.NotBlank;

public class ModifyPasswordReq {

    @NotBlank(message = "")
    private String oldPassword;
    @NotBlank(message = "")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
