package com.test.fakebook.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Entity class representing a user role
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {
	
	// Primary key for the role
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	// Unique name of the role
	@Column(unique = true, nullable = false)
	private String name;
	
	// Many-to-many relationship with users
	@ManyToMany(mappedBy = "roles")
	private Set<User> users = new HashSet<>();
}

