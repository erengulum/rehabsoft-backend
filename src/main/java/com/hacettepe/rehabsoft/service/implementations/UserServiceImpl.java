package com.hacettepe.rehabsoft.service.implementations;

import com.hacettepe.rehabsoft.dto.RegistrationRequest;
import com.hacettepe.rehabsoft.dto.UserDto;
import com.hacettepe.rehabsoft.entity.Role;
import com.hacettepe.rehabsoft.entity.User;
import com.hacettepe.rehabsoft.repository.RoleRepository;
import com.hacettepe.rehabsoft.repository.UserRepository;
import com.hacettepe.rehabsoft.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Slf4j
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            //authorities.add(new SimpleGrantedAuthority(role.getName()));
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
        //return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }


    @Override
    public UserDto getById(Long id) {
        User tempUser = userRepository.getOne(id);
        return modelMapper.map(tempUser, UserDto.class);
    }

    @Override
    public UserDto getByUsername(String username) {
        User tempUser = userRepository.findByUsername(username);
        return modelMapper.map(tempUser, UserDto.class);
    }

    @Override
    public UserDto save(UserDto user) {
        //Transforms UserDto to the User object and save it
        User tempUser = modelMapper.map(user, User.class);
        tempUser = userRepository.save(tempUser);
        user.setId(tempUser.getId());
        return user;
    }



    //DİGER KONTROLLERİ DE YAP!!! USERNAME ZATEN VAR GİBİ VS
    //Gelen registerDto'sunu veritabanına kaydediyoruz
    @Transactional
    public Boolean register(RegistrationRequest registrationRequest) {
        try {
            User user = new User();
            user.setEmail(registrationRequest.getEmail());
            user.setNameSurname(registrationRequest.getNameSurname());
            //Password'u bcrypt ile sifreleyip kaydediyoruz
            user.setPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
            user.setUsername(registrationRequest.getUsername());
            user.setEnabled(true);
            Set<Role> roleSet = new HashSet<Role>();
            // Use add() method to add elements into the Set
            roleSet.add(roleRepository.findByName("USER"));
            user.setRoles(roleSet);
            userRepository.save(user);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error("REGISTRATION=>", e);
            return Boolean.FALSE;
        }
    }


}
