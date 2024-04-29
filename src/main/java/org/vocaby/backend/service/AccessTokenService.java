package org.vocaby.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.vocaby.backend.config.AccessTokenConfiguration;
import org.vocaby.backend.dto.AccessTokenDto;
import org.vocaby.backend.entity.User;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccessTokenService {

    @Value("${application.host}")
    private String host;

    private final AccessTokenConfiguration accessTokenConfiguration;

    /**
     * Extracts id of the user, for which the token was issued.
     * @param  token JWT token string
     * @return subject claim of the JWT token as {@link Integer}
     */
    public int extractSubject(final String token) {
        final String subjectClaim = extractClaim(token, Claims::getSubject);

        if (subjectClaim == null) {
            return 0;
        }

        return Integer.parseInt(subjectClaim);
    }

    /**
     * Extracts all claims from JWT token.
     * @param  token JWT token string
     * @return all claims as {@link Claims} object
     */
    private Claims extractAllClaims(final String token) {
        return Jwts
                .parser()
                .verifyWith((SecretKey) getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * Extracts a concrete claim from JWT token.
     * @param  token          JWT token string
     * @param  claimsResolver function, that extracts the claim
     * @return claim value
     * @param  <T> type of the claim value
     */
    private <T> T extractClaim(final String token,
                               final Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    /**
     * Generates JWT token for the given user.
     * @param  user user entity
     * @return JWT token string
     */
    private AccessTokenDto generateToken(final User user) {
        log.info("Generating JWT token for user `{}`", user.getId());

        final Date tokenExpiration = new Date(System.currentTimeMillis()
            + accessTokenConfiguration.getLifetimeInSeconds() * 1000);

        final String token = Jwts
                .builder()
                .claim("aud", host)
                .subject(String.valueOf(user.getId()))
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(tokenExpiration)
                .signWith(getSignInKey())
                .compact();

        log.info("Successfully generated JWT token `{}` for user `{}`",
            token, user.getId());

        return AccessTokenDto
            .builder()
            .accessToken(token)
            .build();
    }

    /**
     * @return {@link SecretKey} object, used to sign and verify JWT tokens
     */
    private Key getSignInKey() {
        return new SecretKey() {

            @Override
            public String getAlgorithm() {
                return "HmacSHA512";
            }

            @Override
            public String getFormat() {
                return "RAW";
            }

            @Override
            public byte[] getEncoded() {
                return accessTokenConfiguration.getSecretKey().getBytes();
            }
        };
    }
}
