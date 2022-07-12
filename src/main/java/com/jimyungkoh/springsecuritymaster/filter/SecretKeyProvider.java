package com.jimyungkoh.springsecuritymaster.filter;

import com.jimyungkoh.springsecuritymaster.constants.SecurityConstants;
import io.jsonwebtoken.security.Keys;
import org.jetbrains.annotations.NotNull;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

public class SecretKeyProvider {
    @NotNull
    public static SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                SecurityConstants.JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }
}
