package com.github.jbrasileiro.dainichi.ruling;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.dainichi.orm.entity.Ruling;
import com.github.jbrasileiro.dainichi.orm.repository.RulingRepository;


class RulingServiceDefaultTest {


	@Mock
	private RulingRepository repository;
	private RulingServiceDefault service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		service = new RulingServiceDefault(repository);
	}

	@Test
	void testSaveRuling() {
		Ruling entity = new Ruling();
		Ruling result = service.save(entity);
		Mockito.verify(repository).save(ArgumentMatchers.same(entity));
		Assertions.assertNull(result);

	}

	@Test
	void testUpdate() {
		Ruling entity = new Ruling();
		entity.setName("any name 00");

		Mockito.when(repository.findById(ArgumentMatchers.any(Integer.class)))
			.thenReturn(Optional.of(new Ruling()));

		Ruling result = service.update(10, entity);

		Mockito.verify(repository).findById(10);
		Mockito.verify(repository).save(ArgumentMatchers.eq(entity));
		Assertions.assertNull(result);
	}
}
