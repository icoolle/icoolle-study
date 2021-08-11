package com.icoolle.tool;

import cn.hutool.core.codec.Base64;
import com.google.gson.Gson;
import com.icoolle.component.exception.BaseException;
import com.icoolle.model.ums.po.AuthUserEntity;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.icoolle.common.constant.consts.JwtTokenConst.*;


/**
 * jwt 工具
 */
@Slf4j
@Component
public class JwtTokenProviderTool {

    /**
     * 构建jwt
     * @return
     */
    public static String createJWT(AuthUserEntity authUserEntity) {
        try {
            // 使用HS256加密算法
            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
            //生成签名密钥
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(APPSECRET_KEY);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
            //userId是重要信息，进行加密下
            String entityId = Base64.encode(authUserEntity.getId().toString());
            // 保存信息
            Map<String, Object> map = new HashMap<>(16);
            Gson gson = GsonUtil.getGson();
            // 当前登录用户信息
            map.put("password", authUserEntity.getPassword());
            map.put("id", authUserEntity.getId());
            //添加构成JWT的参数
            JwtBuilder builder = Jwts.builder()
                    .setId(entityId)
                    // 可以将基本不重要的对象信息放到claims
                    .setClaims(map)
                    // 代表这个JWT的主体，即它的所有人
                    .setSubject(authUserEntity.getUserName())
                    // 代表这个JWT的签发主体；
                    .setIssuer(SUBJECT)
                    // 是一个时间戳，代表这个JWT的签发时间；
                    .setIssuedAt(new Date())
                    // 代表这个JWT的接收对象；
                    .setAudience(entityId)
                    .signWith(signatureAlgorithm, signingKey);
            //添加Token过期时间
            if (EXPIRITION >= 0) {
                long expMillis = System.currentTimeMillis() + EXPIRITION;
                Date exp = new Date(expMillis);
                // 是一个时间戳，代表这个JWT的过期时间；
                builder.setExpiration(exp)
                        // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
                        .setNotBefore(new Date());
            }
            //生成JWT
            return TOKEN_PREFIX + builder.compact();
        } catch (Exception e) {
            log.error("签名失败{}", e.getMessage());
            throw new BaseException(521, "创建Token签名失败，请检查登录用户信息");
        }
    }

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @return
     */
    public static Claims parseJWT(String jsonWebToken) {
        String token = replaceToken(jsonWebToken);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(APPSECRET_KEY))
                    .parseClaimsJws(token).getBody();
            return claims;
        } catch (ExpiredJwtException eje) {
            return  eje.getClaims();
        } catch (Exception e) {
            log.error("===== token解析异常 =====  >>>{}", e.getMessage());
            throw new BaseException(999, "token解析异常");
        }
    }

    /**
     * 解析jwt
     *
     * @param jsonWebToken
     * @return
     */
    public static String parseJWTGetId(String jsonWebToken) {
        String token = replaceToken(jsonWebToken);
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(APPSECRET_KEY))
                    .parseClaimsJws(token).getBody();
            Map<String, Object> map = claims;
            return String.valueOf(map.get("id"));
        } catch (ExpiredJwtException eje) {
            Map<String, Object> map = eje.getClaims();
            return String.valueOf(map.get("id"));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从token中获取用户名
     *
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        return parseJWT(token).getSubject();
    }

    /**
     * 从token中获取用户ID
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        String userId = parseJWT(token).getAudience();
        return Base64.decodeStr(userId);
    }

    /**
     * 是否已过期
     *
     * @param token
     * @return
     */
    public static boolean isExpiration(String token) {
        return parseJWT(token).getExpiration().before(new Date());
    }

    /**
     * 验证令牌
     *
     * @param token 令牌
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String userName) {
        return (userName.equals(getUsername(token)) && !isExpiration(token));
    }

    /**
     * 替换开头字段
     *
     * @param token
     * @return
     */
    private static String replaceToken(String token) {
        return token.replace(TOKEN_PREFIX, "");
    }




}
