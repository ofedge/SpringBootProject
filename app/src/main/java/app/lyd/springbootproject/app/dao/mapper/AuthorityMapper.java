package app.lyd.springbootproject.app.dao.mapper;

import app.lyd.springbootproject.app.model.Authority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AuthorityMapper {

    @Select({
            "select id, name, description from t_authority where id in (",
            "select a.id from t_user_role ur join t_role_authority ra on ur.role_id = ra.role_id ",
            "join t_authority a on ra.authority_id = a.id where ur.user_id = #{userId})"
    })
    List<Authority> findByUserId(Long userId);
}
