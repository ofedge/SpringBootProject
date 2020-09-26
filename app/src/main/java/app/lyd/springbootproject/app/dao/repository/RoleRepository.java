package app.lyd.springbootproject.app.dao.repository;

import app.lyd.springbootproject.app.dao.mapper.RoleMapper;
import app.lyd.springbootproject.app.model.Role;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = RoleRepository.ROLE_CACHE, cacheManager = "cacheManager")
public class RoleRepository {

    static final String ROLE_CACHE = "roleCache";

    private final RoleMapper roleMapper;

    public RoleRepository(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Cacheable(key = "#userId")
    public List<Role> findByUserId(Long userId) {
        return roleMapper.findByUserId(userId);
    }
}
