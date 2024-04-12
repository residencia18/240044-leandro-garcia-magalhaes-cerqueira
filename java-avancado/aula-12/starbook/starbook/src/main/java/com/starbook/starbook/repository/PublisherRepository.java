package com.starbook.starbook.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.starbook.starbook.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository <Publisher, Long>{
	
	Optional<Publisher> findByName(String name);

}
