package com.exemplo.olamundo.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OlaMundoController {

    @GetMapping("/ola")
    public String olaMundo() {
        return "Olá Mundo!" +  LocalDateTime.now();
    }
}
