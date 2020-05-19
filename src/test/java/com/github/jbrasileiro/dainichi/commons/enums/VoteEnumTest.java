package com.github.jbrasileiro.dainichi.commons.enums;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class VoteEnumTest {

	@Test
	void valueFromWithNull() {
		VoteEnum result = VoteEnum.valueFrom(null);
		Assertions.assertEquals(VoteEnum.NO, result);
	}

	@Test
	void valueFromWithTrue() {
		VoteEnum result = VoteEnum.valueFrom(true);
		Assertions.assertEquals(VoteEnum.YES, result);
	}

	@Test
	void valueFromWithFalse() {
		VoteEnum result = VoteEnum.valueFrom(false);
		Assertions.assertEquals(VoteEnum.NO, result);
	}
}
