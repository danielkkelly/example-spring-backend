package com.danielkkelly.example.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.validation.Valid;

import com.danielkkelly.example.domain.dto.AuthenticationRequest;
import com.danielkkelly.example.domain.dto.UserCreateRequest;
import com.danielkkelly.example.domain.dto.UserView;
import com.danielkkelly.example.domain.mapper.UserViewMapper;
import com.danielkkelly.example.domain.model.User;
import com.danielkkelly.example.service.UserService;

import java.time.Instant;

import static java.util.stream.Collectors.joining;

@Slf4j
@Tag(name = "Authentication")
@RestController
public class AuthenticationController {
    @Inject
    private AuthenticationManager authenticationManager;

    @Inject
    private JwtEncoder jwtEncoder;

    @Inject
    private UserViewMapper userViewMapper;

    @Inject
    private UserService userService; 

    @PostMapping("tokens")
    public ResponseEntity<UserView> signOn(@RequestBody @Valid AuthenticationRequest request) {
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));

            User user = (User) authentication.getPrincipal();
            log.debug(user.toString());

            Instant now = Instant.now();
            long expiry = 36000L;

            String scope = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(joining(" "));

            JwtClaimsSet claims = JwtClaimsSet.builder()
                    .issuer("com.danielkkelly")
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiry))
                    .subject(user.getUsername())
                    .claim("scope", scope)
                    .build();

            String token = this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, token)
                    .body(userViewMapper.toView(user));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("registrations")
    public UserView signUp(@RequestBody @Valid UserCreateRequest request) {
        return userViewMapper.toView(userService.create(request));
    }
}