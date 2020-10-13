package app.lyd.springbootproject.auth.service;

import app.lyd.springbootproject.auth.pojo.BaseUser;

/**
 * Implement this interface for login authorization
 *
 * @param <T> model extends BaseUser
 */
public interface BaseAuthorizationService<T extends BaseUser> {

    /**
     * verify token and return the token user
     * @param token
     * @return
     */
    T parseBearerToken(String token);
}
