package com.github.jbrasileiro.dainichi.voting;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;

class VoteRequestTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verify(VoteRequest.class);
	}
}
