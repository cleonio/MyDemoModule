package com.cleo.jwt;

import com.cleo.constant.SecurityConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

/**
 * @className: com.cleo.jwt-> JwtUtil
 * @description:
 * @author: cleo
 * @createDate: 2021-10-28 16:32
 * @version: 1.0
 */

@Slf4j
public class JwtUtil {

    /**
     * 由字符串生成加密key
     *
     * @return SecretKey
     */
    public static SecretKey generalKey() {
        //本地的密码解码
        byte[] encodedKey = Base64.decodeBase64(SecurityConstants.JWT_SECRET);
        // 根据给定的字节数组使用AES加密算法构造一个密钥
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * * 创建jwt
     *
     * @param claims    额外参数 创建payload的私有声明
     *                  （根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
     * @param issuer    jwt签发人
     * @param subject   代表这个JWT的主体
     * @param ttlMillis jwt的签发时间
     * @return String
     * @throws Exception
     */
    public static String createJWT(Map<String, Object> claims, String issuer, String subject, long ttlMillis)
            throws Exception {
        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成JWT的时间
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        //生成签名的时候使用的秘钥secret，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。
        //一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();

        //下面就是在为payload添加各种标准声明和私有声明了
        JwtBuilder builder = Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setClaims(claims)   // 如果有私有声明，一定要先设置这个自己创建的私有的声明，这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setId(SecurityConstants.JWT_ID)    // 设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setIssuedAt(now) // iat: jwt的签发时间
                .setIssuer(issuer) // issuer：jwt签发人
                .setSubject(subject) // sub(Subject)：代表这个JWT的主体，即它的所有人，这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志。
                .signWith(signatureAlgorithm, key); // 设置签名使用的签名算法和签名使用的秘钥
        // 设置过期时间
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 解密jwt
     *
     * @param jwt jwt token数据
     * @return Claims
     * @throws Exception
     */
    public static Claims parseJWT(String jwt) throws Exception {
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        try {
            Claims claims = Jwts.parser() //得到DefaultJwtParser
                    .setSigningKey(key) //设置签名的秘钥
                    .parseClaimsJws(jwt).getBody();//设置需要解析的jwt
            return claims;
        } catch (Exception e) {
            log.error("parseJWT error :{}",e.getMessage());
            throw new RuntimeException("token parse error");
        }
    }
}
