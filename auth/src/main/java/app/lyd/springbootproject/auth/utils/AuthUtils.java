package app.lyd.springbootproject.auth.utils;

import app.lyd.springbootproject.auth.property.AuthProperty;
import app.lyd.springbootproject.base.utils.MD5Utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

;
public class AuthUtils {

    public static String encryptPassword(String password) {
        return MD5Utils.md5(MD5Utils.md5(password + AuthProperty.PASSWORD_SALT) + AuthProperty.PASSWORD_SALT);
    }

    /**
     * generate a token with payload
     *
     * @param payload JWT payload
     * @param expireAfterSeconds expired after seconds
     * @return
     */
    public static String generateToken(Map<String, Object> payload, Long expireAfterSeconds) {
        JwtBuilder builder = Jwts.builder()
                .setId(AuthProperty.JWT_ID)
                .setSubject(AuthProperty.JWT_PASSWORD)
                .setIssuedAt(new Date())
                .setExpiration(expireAfterSeconds == null ? null : new Date(new Date().getTime() + expireAfterSeconds * 1000))
                .setClaims(payload)
                .signWith(SignatureAlgorithm.HS256, AuthProperty.JWT_SECRET);
        return builder.compact();
    }

    /**
     * verify token and get the token payload
     *
     * @param token
     * @return
     */
    public static Claims verifyToken(String token) {
        return Jwts.parser().setSigningKey(AuthProperty.JWT_SECRET).parseClaimsJws(token).getBody();
    }
}
