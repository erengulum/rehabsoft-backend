package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    List<User> findAll();

}
