package com.github.jbrasileiro.dainichi.orm.entity;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;

class SessionTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verifyEntity(Session.class);
	}
}
