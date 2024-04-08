package com.starbook.starbook.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor 
@Data
@Entity(name = "PUBLISHER")
public class Publisher {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty
	private String name;
	
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Book> books;
	

}
