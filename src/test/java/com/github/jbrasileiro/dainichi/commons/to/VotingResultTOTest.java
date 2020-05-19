package com.github.jbrasileiro.dainichi.commons.to;

import org.junit.jupiter.api.Test;

import com.github.jbrasileiro.dainichi.DefaultEqualsVerifier;

class VotingResultTOTest {

	@Test
	void equalsVerifier() {
		DefaultEqualsVerifier.verify(VotingResultTO.class);
	}
}
