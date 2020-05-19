package com.github.jbrasileiro.dainichi.commons.enums;

import org.apache.commons.lang3.BooleanUtils;

public enum VoteEnum {
	YES,
	NO,
	;

	public static VoteEnum valueFrom(
		final Boolean value) {
		if(BooleanUtils.isTrue(value)) {
			return YES;
		} else {
			return NO;
		}
	}
}
