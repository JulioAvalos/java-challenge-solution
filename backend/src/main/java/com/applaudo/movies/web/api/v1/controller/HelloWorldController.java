package com.applaudo.movies.web.api.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/")
public class HelloWorldController {

    @GetMapping
    public String hello() {
        return "<b>Movies App: Hello Home! at " + LocalDateTime.now() + "</b>";
    }

}
