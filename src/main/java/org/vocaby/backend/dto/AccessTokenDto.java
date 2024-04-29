package org.vocaby.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class AccessTokenDto {

    private String accessToken;
    private Date accessTokenExpiry;
}
