package app.lyd.springbootproject.app.dao.mapper;

import app.lyd.springbootproject.app.dao.entity.UserInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    String USER_INFO = "userInfo";

    @Select({
            "select id, username, password, name, email, active, create_time, last_modify from t_user ",
            "where username = #{username}"
    })
    @Results(id = USER_INFO, value = {
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_modify", property = "lastModify")
    })
    UserInfo findByUsername(String username);

    @Select("select id, username, name, email, active, create_time, last_modify from t_user where id = #{userId}")
    @ResultMap(USER_INFO)
    UserInfo findById(long userId);
}
