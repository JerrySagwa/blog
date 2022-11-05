package com.sagwa.blog.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Sagwa
 * @Date 2022/11/4 22:08
 * @ClassName JWTUtils
 */
public class JWTUtils {

    // A + B + 密钥(JWTToken)
    private static final String jwtToken = "123456Sagwa!@#$$";

    // 生成密钥
    public static String createToken(Long userId){
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",userId);
        JwtBuilder jwtBuilder = Jwts.builder()
                // Header {"type":"JWT","alg":"HS256"} 固定
                .signWith(SignatureAlgorithm.HS256, jwtToken) // 签发算法，秘钥为jwtToken
                // body
                .setClaims(claims) // body数据，要唯一，自行设置
                .setIssuedAt(new Date()) // 设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 60 * 1000));// 一天的有效时间
        String token = jwtBuilder.compact();
        return token;
    }

    public static Map<String, Object> checkToken(String token){
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Test for JWTUtils
     * @param args
     */
    public static void main(String[] args) {
        String token = createToken(100L);
        System.out.println("token = " + token);
        Map<String, Object> map = checkToken(token);
        System.out.println("userId = " + map.get("userId"));
    }

}

