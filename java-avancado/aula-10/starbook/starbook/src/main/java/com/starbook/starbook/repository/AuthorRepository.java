package com.starbook.starbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.starbook.starbook.model.Author;

public interface AuthorRepository extends JpaRepository <Author, Long> {

}
