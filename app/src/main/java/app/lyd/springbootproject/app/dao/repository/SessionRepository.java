package app.lyd.springbootproject.app.dao.repository;

import app.lyd.springbootproject.auth.property.AuthProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
@CacheConfig(cacheNames = SessionRepository.CACHE_NAME, cacheManager = "sessionCacheManager")
public class SessionRepository {

    private final Logger logger = LoggerFactory.getLogger(SessionRepository.class);

    static final String CACHE_NAME = "session";
    private Map<Long, List<String>> tokenMap = new ConcurrentHashMap<>();
    private Cache cache;

    public SessionRepository(@Qualifier("sessionCacheManager")CacheManager cacheManager) {
        this.cache = cacheManager.getCache(CACHE_NAME);
    }

    @CachePut(key = "#token")
    public Long save(String token, Long userId) {
        List<String> tokens = tokenMap.computeIfAbsent(userId, k -> new ArrayList<String>());
        // clear expired tokens while login
        tokens.removeIf(userToken -> cache.get(userToken) == null);
        if (tokens.size() >= AuthProperty.LOGIN_LIMIT) {
            cache.evict(tokens.remove(0));
        }
        tokens.add(token);
        return userId;
    }

    @Cacheable(key = "#token")
    public Long load(String token, Long userId) {
        return null;
    }

    @CacheEvict(key = "#token")
    public void delete(String token, Long userId) {
        tokenMap.get(userId).remove(token);
    }

    public void delete(Long userId) {
        List<String> tokens = tokenMap.remove(userId);
        if (tokens != null) {
            tokens.forEach(cache::evict);
        }
    }

    @Scheduled(cron = "0 0/1 0 * * ?")
    public void clearToken() {
        logger.info("Start clean session cache");
        tokenMap.values().forEach(tokens -> tokens.removeIf(token -> cache.get(token) == null));
    }

}
