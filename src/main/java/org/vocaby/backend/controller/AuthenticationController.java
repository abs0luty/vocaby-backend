package org.vocaby.backend.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vocaby.backend.dto.AccessTokenDto;
import org.vocaby.backend.dto.SignInRequestDto;
import org.vocaby.backend.dto.SignUpRequestDto;

@RequestMapping("/api/v1/auth")
@RestController
@SecurityRequirements({})
public class AuthenticationController {

    @PostMapping("/signin")
    ResponseEntity<AccessTokenDto> signIn(@RequestBody final SignInRequestDto requestDto) {
        return null;
    }

    @PostMapping("/signup")
    ResponseEntity<AccessTokenDto> signUp(@RequestBody final SignUpRequestDto requestDto) {
        return null;
    }
}
