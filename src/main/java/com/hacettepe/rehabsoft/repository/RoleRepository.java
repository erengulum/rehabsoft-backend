package com.hacettepe.rehabsoft.repository;

import com.hacettepe.rehabsoft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String roleName);
}
