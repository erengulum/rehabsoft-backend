package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
}
