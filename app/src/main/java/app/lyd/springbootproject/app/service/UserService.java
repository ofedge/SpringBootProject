package app.lyd.springbootproject.app.service;

import app.lyd.springbootproject.app.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

}
