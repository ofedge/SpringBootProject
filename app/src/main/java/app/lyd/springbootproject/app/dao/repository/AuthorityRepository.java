package app.lyd.springbootproject.app.dao.repository;

import app.lyd.springbootproject.app.dao.mapper.AuthorityMapper;
import app.lyd.springbootproject.app.model.Authority;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@CacheConfig(cacheNames = AuthorityRepository.AUTHORITY_CACHE, cacheManager = "cacheManager")
public class AuthorityRepository {

    static final String AUTHORITY_CACHE = "authorityCache";

    private final AuthorityMapper authorityMapper;

    public AuthorityRepository(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }

    @Cacheable(key = "#userId")
    public List<Authority> findByUserId(Long userId) {
        return authorityMapper.findByUserId(userId);
    }
}
