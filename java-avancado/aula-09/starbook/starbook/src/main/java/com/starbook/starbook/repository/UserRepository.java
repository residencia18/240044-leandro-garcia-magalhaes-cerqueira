package com.starbook.starbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starbook.starbook.model.User;

public interface UserRepository extends JpaRepository <User, Long> {

}
