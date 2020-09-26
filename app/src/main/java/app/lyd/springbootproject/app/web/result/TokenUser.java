package app.lyd.springbootproject.app.web.result;

import app.lyd.springbootproject.app.dao.entity.UserInfo;
import app.lyd.springbootproject.app.model.Authority;
import app.lyd.springbootproject.app.model.Role;
import app.lyd.springbootproject.auth.pojo.BaseUser;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenUser extends BaseUser {
    private String username;
    private String name;
    private String email;
    private String active;
    private Long createTime;
    private Long lastModify;
    private Long loginTime;
    private List<Role> roleList;
    private List<Authority> authorityList;

    public TokenUser() {
    }

    public TokenUser(UserInfo userInfo) {
        this.userId = userInfo.getId();
        this.username = userInfo.getUsername();
        this.name = userInfo.getName();
        this.email = userInfo.getEmail();
        this.active = userInfo.getActive();
        this.createTime = userInfo.getCreateTime();
        this.lastModify = userInfo.getLastModify();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLastModify() {
        return lastModify;
    }

    public void setLastModify(Long lastModify) {
        this.lastModify = lastModify;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
    }

    public void setAuthorityList(List<Authority> authorityList) {
        this.authorityList = authorityList;
    }
}
