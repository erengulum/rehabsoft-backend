package com.hacettepe.rehabsoft.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api") //Token pathine gelen bütün isteklere izin verilecek.Bunun ayarı SecurityConfig'de
@Api(value = "/api")
public class DenemeController {

    @PreAuthorize("hasRole('ROLE_USER')")
    @GetMapping(value = "/deneme")
    public ResponseEntity<Boolean> deneme() {

        log.warn("Deneme metodu basariyla calisti");

        return ResponseEntity.ok(true);
    }
}
