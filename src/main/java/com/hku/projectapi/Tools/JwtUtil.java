package com.hku.projectapi.Tools;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 过期5分钟
     * */
    private static final long EXPIRE_TIME = 30 * 60 * 1000;
//    private static final long EXPIRE_TIME = 60 * 1000 * 100000;

    /**
     * jwt密钥
     * */
    private static final String SECRET = "ace_coder_token_key";

    /**
     * 生成jwt字符串，五分钟后过期  JWT(json web token)
     * @param userId
     * @param info,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and Date
     * @return
     * */
    public static String sign(String userId, Map<String, Object> info) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(userId)
                    //存放自定义数据
                    .withClaim("info", info)
                    //五分钟后token过期
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    public static String updateToken(String token)
    {
        long expiry = Long.valueOf(JWT.decode(token).getClaim("exp").toString());
        long current = System.currentTimeMillis() / 1000;
        // 3 minute left, update
        if(expiry - current < 3 * 60)
        {
            String userId = JWT.decode(token).getAudience().get(0);
            Map<String, Object> info = JWT.decode(token).getClaim("info").asMap();
            String newToken = sign(userId, info);
            return newToken;
        }
        return token;
    }


    public static boolean checkSign(String token) {
        try {
            Algorithm algorithm  = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    //.withClaim("username, username)
                    .build();
            verifier.verify(token);
            return true;
        }catch (JWTVerificationException e) {
            throw new RuntimeException("Invalid token, please re-obtain.");
        }
    }
}


