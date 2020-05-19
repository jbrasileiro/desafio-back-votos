package com.github.jbrasileiro.dainichi.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.jbrasileiro.dainichi.orm.entity.Associate;

public interface AssociateRepository
	extends
	JpaRepository<Associate, Integer> {
}
