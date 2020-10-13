package app.lyd.springbootproject.app.service;

import app.lyd.springbootproject.app.dao.mapper.UserMapper;
import app.lyd.springbootproject.app.web.query.UserInfoQuery;
import app.lyd.springbootproject.app.web.result.UserInfoResult;
import app.lyd.springbootproject.base.web.result.PageResult;
import app.lyd.springbootproject.dao.annotation.PageQuery;
import app.lyd.springbootproject.dao.util.PageUtil;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PageQuery
    public PageResult<UserInfoResult> getUsersByCondition(UserInfoQuery query) {
        return PageUtil.parseResult(userMapper.findPageByCondition(query), UserInfoResult::new);
    }
}
