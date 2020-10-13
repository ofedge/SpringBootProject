package app.lyd.springbootproject.app.dao.mapper;

import app.lyd.springbootproject.app.dao.entity.UserInfo;
import app.lyd.springbootproject.app.dao.entity.UserPassword;
import app.lyd.springbootproject.app.web.query.UserInfoQuery;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    String USER_INFO = "userInfo";
    String USER_INFO_SQL = "select id, username, password, name, email, active, create_time, last_modify from t_user ";

    @Select({ USER_INFO_SQL, "where username = #{username}" })
    @Results(id = USER_INFO, value = {
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "last_modify", property = "lastModify")
    })
    UserInfo findByUsername(String username);

    @Select("select id, username, name, email, active, create_time, last_modify from t_user where id = #{userId}")
    @ResultMap(USER_INFO)
    UserInfo findById(long userId);

    @Select({
            "<script>",
            USER_INFO_SQL,
            "<where>",
            "<if test=\"username != null and username != ''\">and username like concat('%', #{username} , '%')</if>",
            "<if test=\"name != null and name != ''\">and name like concat('%', #{name}, '%')</if>",
            "<if test=\"email != null and email != ''\">and email like concat('%', #{email}, '%')</if>",
            "<if test=\"active != null and active != ''\">and active = #{active}</if>",
            "<if test=\"createTimeStart != null and createTimeStart != ''\">and create_time > #{createTimeStart}</if>",
            "<if test=\"createTimeEnd != null and createTimeEnd != ''\">and create_time &lt; #{createTimeEnd}</if>",
            "<if test=\"lastModifyStart != null and lastModifyStart != ''\">and last_modify > #{lastModifyStart}</if>",
            "<if test=\"lastModifyEnd != null and lastModifyEnd != ''\">and last_modify &lt; #{lastModifyEnd}</if>",
            "</where>",
            "</script>"
    })
    @ResultMap(USER_INFO)
    Page<UserInfo> findPageByCondition(UserInfoQuery userInfoQuery);

    @Select("select id, password from t_user where id = #{userId}")
    UserPassword findUserPassById(long userId);

    @Update({"update t_user set password = #{password} where id = #{id}"})
    void updatePassword(UserPassword userPassword);
}
