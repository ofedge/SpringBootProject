package app.lyd.springbootproject.app.web.result;

import app.lyd.springbootproject.app.dao.entity.UserInfo;

public class UserInfoResult {

    private Long id;
    private String username;
    private String name;
    private String email;
    private String active;
    private Long createTime;
    private Long lastModify;

    public UserInfoResult(UserInfo userInfo) {
        this.id = userInfo.getId();
        this.username = userInfo.getUsername();
        this.name = userInfo.getName();
        this.email = userInfo.getEmail();
        this.active = userInfo.getActive();
        this.createTime = userInfo.getCreateTime();
        this.lastModify = userInfo.getLastModify();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getActive() {
        return active;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public Long getLastModify() {
        return lastModify;
    }
}
