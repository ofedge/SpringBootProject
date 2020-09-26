package app.lyd.springbootproject.app.dao.mapper;

import app.lyd.springbootproject.app.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMapper {

    @Select({
            "select r.id, r.name, r.description ",
            "from t_role r join t_user_role ur on r.id = ur.role_id ",
            "where ur.user_id = #{userId}"
    })
    List<Role> findByUserId(Long userId);
}
