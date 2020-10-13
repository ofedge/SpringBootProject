package app.lyd.springbootproject.app.service;

import app.lyd.springbootproject.app.config.exception.SbpException;
import app.lyd.springbootproject.app.consts.ErrorCode;
import app.lyd.springbootproject.app.dao.entity.UserInfo;
import app.lyd.springbootproject.app.dao.entity.UserPassword;
import app.lyd.springbootproject.app.dao.mapper.UserMapper;
import app.lyd.springbootproject.app.dao.repository.AuthorityRepository;
import app.lyd.springbootproject.app.dao.repository.RoleRepository;
import app.lyd.springbootproject.app.dao.repository.SessionRepository;
import app.lyd.springbootproject.app.web.req.LoginReq;
import app.lyd.springbootproject.app.web.req.ModifyPasswordReq;
import app.lyd.springbootproject.app.web.result.TokenUser;
import app.lyd.springbootproject.auth.service.BaseAuthorizationService;
import app.lyd.springbootproject.auth.utils.AuthUtils;
import app.lyd.springbootproject.app.consts.UserActive;
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
                .orElseThrow(() -> new SbpException(ErrorCode.TOKEN_INVALID));
        if (!req.getPassword().equals(userInfo.getPassword())) {
            throw new SbpException(ErrorCode.AUTH_INVALID);
        }
        if (!UserActive.ACTIVE.equals(userInfo.getActive())) {
            throw new SbpException(ErrorCode.AUTH_LOCKED);
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

    public TokenUser buildTokenUser(TokenUser tokenUser) throws Exception {
        Long loginTime = tokenUser.getLoginTime();
        UserInfo userInfo = Optional.ofNullable(userMapper.findById(tokenUser.getUserId())).orElseThrow(() -> new SbpException(ErrorCode.TOKEN_INVALID));
        tokenUser = new TokenUser(userInfo);
        tokenUser.setLoginTime(loginTime);
        this.setUserRoleAndAuthorities(userInfo, tokenUser.getToken(), tokenUser);
        return tokenUser;
    }

    public void signOut(TokenUser tokenUser) {
        sessionRepository.delete(tokenUser.getToken(), tokenUser.getUserId());
    }

    @Override
    public TokenUser parseBearerToken(String token) {
        try {
            if (token == null) {
                throw new SbpException(ErrorCode.TOKEN_NOT_EXISTS);
            }
            if (!token.startsWith("Bearer ")) {
                throw new SbpException(ErrorCode.TOKEN_INVALID);
            }

            token = token.replace("Bearer ", "");
            Claims claims = AuthUtils.verifyToken(token);
            Long userId = Optional.ofNullable(sessionRepository.load(token, new Long((Integer) claims.get("userId"))))
                    .orElseThrow(() -> new SbpException(ErrorCode.TOKEN_EXPIRED));
            Long loginTime = (Long) claims.get("loginTime");
            TokenUser tokenUser = new TokenUser();
            tokenUser.setUserId(userId);
            tokenUser.setToken(token);
            tokenUser.setLoginTime(loginTime);
            return tokenUser;
        } catch(SbpException e) {
            throw new SbpException(e.getErrorCode());
        } catch (Exception e) {
            e.printStackTrace();
            throw new SbpException(ErrorCode.TOKEN_INVALID);
        }
    }

    public void updatePassword(TokenUser tokenUser, ModifyPasswordReq req) {
        UserPassword userPassword = userMapper.findUserPassById(tokenUser.getUserId());
        if (!req.getOldPassword().equals(userPassword.getPassword())) {
            throw new SbpException(ErrorCode.PASSWORD_OLD_WRONG);
        }
        userPassword.setPassword(req.getNewPassword());
        userMapper.updatePassword(userPassword);
        sessionRepository.delete(tokenUser.getUserId());
    }
}
