package com.applaudo.movies.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final ConsumerTokenServices tokenServices;

    @GetMapping("/revoke/{tokenId:.*}")
    public void revokeToken(@PathVariable("tokenId") String token) {
        tokenServices.revokeToken(token);
    }
}
