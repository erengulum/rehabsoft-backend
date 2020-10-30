package com.hacettepe.rehabsoft.controller;

import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.service.UserService;
import com.hacettepe.rehabsoft.util.ApiPaths;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


//@CrossOrigin(origins = "*", maxAge = 3600)
@Slf4j
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RequestMapping(ApiPaths.UserPath.CTRL)
@RestController
@Api(value = "/api/users")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> getOne(@PathVariable(value = "id") Long id){
        log.warn("GetOne(User icin) metodu basariyla calisti");
        return ResponseEntity.ok(userService.getById(id));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> listUsers(){
        log.warn("listUsers(Admin icin) metodu basariyla calisti");
        List<UserDto> data = userService.getAll();
        return ResponseEntity.ok(data);
    }


    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto user){
        return userService.save(user);
    }



}
