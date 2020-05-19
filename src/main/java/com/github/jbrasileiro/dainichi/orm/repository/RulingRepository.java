package com.github.jbrasileiro.dainichi.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbrasileiro.dainichi.orm.entity.Ruling;


public interface RulingRepository
	extends
	JpaRepository<Ruling, Integer> {
}
