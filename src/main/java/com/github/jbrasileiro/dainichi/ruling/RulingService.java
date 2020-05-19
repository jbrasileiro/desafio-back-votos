package com.github.jbrasileiro.dainichi.ruling;

import java.util.Collection;

import com.github.jbrasileiro.dainichi.orm.entity.Ruling;

public interface RulingService {

	Collection<Ruling> findAll();

	Ruling findByID(
		Integer id);

	Ruling save(
		Ruling entity);

	Ruling update(
		Integer id,
		Ruling entity);

	boolean delete(
		Integer id);
}
