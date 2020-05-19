package com.github.jbrasileiro.dainichi.orm.entity;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;

class AssociateTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verifyEntity(Associate.class);
	}
}
