package com.github.jbrasileiro.dainichi.orm.entity;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;


class RulingTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verifyEntity(Ruling.class);
	}
}
