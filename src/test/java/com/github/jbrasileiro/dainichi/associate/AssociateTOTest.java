package com.github.jbrasileiro.dainichi.associate;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;

class AssociateTOTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verify(AssociateTO.class);
	}
}
