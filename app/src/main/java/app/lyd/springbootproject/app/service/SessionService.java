package app.lyd.springbootproject.app.service;

import app.lyd.springbootproject.app.dao.entity.UserInfo;
import app.lyd.springbootproject.app.dao.mapper.UserMapper;
import app.lyd.springbootproject.app.dao.repository.AuthorityRepository;
import app.lyd.springbootproject.app.dao.repository.RoleRepository;
import app.lyd.springbootproject.app.dao.repository.SessionRepository;
import app.lyd.springbootproject.app.web.req.LoginReq;
import app.lyd.springbootproject.app.web.result.TokenUser;
import app.lyd.springbootproject.auth.exception.TokenException;
import app.lyd.springbootproject.auth.service.BaseAuthorizationService;
import app.lyd.springbootproject.auth.utils.AuthUtils;
import app.lyd.springbootproject.app.consts.UserActive;
import app.lyd.springbootproject.auth.exception.AuthException;
import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class SessionService implements BaseAuthorizationService<TokenUser> {

    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final SessionRepository sessionRepository;

    public SessionService(UserMapper userMapper, RoleRepository roleRepository, AuthorityRepository authorityRepository, SessionRepository sessionRepository) {
        this.userMapper = userMapper;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.sessionRepository = sessionRepository;
    }

    public TokenUser signIn(LoginReq req) {
        UserInfo userInfo = Optional.ofNullable(userMapper.findByUsername(req.getUsername()))
                .orElseThrow(() -> new AuthException("Invalid username or password!"));
        if (!req.getPassword().equals(userInfo.getPassword())) {
            throw new AuthException("Invalid username or password!");
        }
        if (!UserActive.ACTIVE.equals(userInfo.getActive())) {
            throw new AuthException("The user has been locked!");
        }
        Map<String, Object> payload = new HashMap<>();
        payload.put("userId", userInfo.getId());
        long loginTime = new Date().getTime();
        payload.put("loginTime", loginTime);
        TokenUser tokenUser = new TokenUser(userInfo);
        tokenUser.setLoginTime(loginTime);
        String token = AuthUtils.generateToken(payload, null);
        this.setUserRoleAndAuthorities(userInfo, token, tokenUser);
        sessionRepository.save(token, userInfo.getId());
        return tokenUser;
    }

    private void setUserRoleAndAuthorities(UserInfo userInfo, String token, TokenUser tokenUser) {
        tokenUser.setToken(token);
        tokenUser.setRoleList(roleRepository.findByUserId(userInfo.getId()));
        tokenUser.setAuthorityList(authorityRepository.findByUserId(userInfo.getId()));
    }

    public void signOut(TokenUser tokenUser) {
        sessionRepository.delete(tokenUser.getToken(), tokenUser.getUserId());
    }

    @Override
    public TokenUser parseToken(String token) {
        try {
            Claims claims = AuthUtils.verifyToken(token);
            Long userId = Optional.ofNullable(sessionRepository.load(token, new Long((Integer) claims.get("userId"))))
                    .orElseThrow(() -> new TokenException("Token Expired!"));
            Long loginTime = (Long) claims.get("loginTime");
            UserInfo userInfo = Optional.ofNullable(userMapper.findById(new Long(userId))).orElseThrow(() -> new Exception());
            TokenUser tokenUser = new TokenUser(userInfo);
            tokenUser.setLoginTime(loginTime);
            this.setUserRoleAndAuthorities(userInfo, token, tokenUser);
            return tokenUser;
        } catch(TokenException e) {
            throw new TokenException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new TokenException("Invalid token!");
        }
    }
}
