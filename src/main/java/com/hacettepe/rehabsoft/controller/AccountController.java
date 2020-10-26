package com.hacettepe.rehabsoft.controller;


import com.hacettepe.rehabsoft.dto.LoginRequest;
import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.TokenResponse;
import com.hacettepe.rehabsoft.entity.Role;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.security.JwtTokenUtil;
import com.hacettepe.rehabsoft.service.implementations.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;



import java.util.Set;

@Slf4j
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token") //Token pathine gelen bütün isteklere izin verilecek.Bunun ayarı SecurityConfig'de
@Api(value = "/api/token")
public class AccountController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(method = RequestMethod.POST) //LoginRequest'i DTO kısmında olusturduk.Front-end'den gelen login objesi gibi düsün
    @ApiOperation(value = "Login Operation", response = TokenResponse.class)
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);

        //Burada sorun olabilir:Bunu yaz:         return ResponseEntity.ok(new AuthToken(token));
        return ResponseEntity.ok(new TokenResponse(loginRequest.getUsername(),token));
    }


    //KAYIT ICIN

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value="Register Operation",response = Boolean.class)
    public ResponseEntity<Boolean> register(@RequestBody RegistrationRequest registrationRequest) throws AuthenticationException {
        log.warn("Kayıt controllerına girdi");
        Boolean response = userService.register(registrationRequest);
        return ResponseEntity.ok(response);
    }
}
