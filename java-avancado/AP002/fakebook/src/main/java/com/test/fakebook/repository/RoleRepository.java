package com.test.fakebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
