package com.example.springsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.entity.Role;



public interface RoleRepository extends JpaRepository<Role, Long> {

}
