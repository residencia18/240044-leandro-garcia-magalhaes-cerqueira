package com.test.fakebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.fakebook.entity.Role;

// Repository interface for Role entity, extending JpaRepository
public interface RoleRepository extends JpaRepository<Role, Long> {

	// Method to find a role by its name
	Role findByName(String string);

}

