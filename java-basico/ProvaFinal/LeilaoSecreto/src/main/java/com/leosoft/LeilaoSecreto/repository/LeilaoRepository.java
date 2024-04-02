package com.leosoft.LeilaoSecreto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leosoft.LeilaoSecreto.model.Leilao;

public interface LeilaoRepository extends JpaRepository <Leilao, Long> {

}
