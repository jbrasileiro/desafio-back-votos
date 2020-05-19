package com.github.jbrasileiro.dainichi;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import nl.jqno.equalsverifier.api.SingleTypeEqualsVerifierApi;

public class DefaultEqualsVerifier {

	private DefaultEqualsVerifier() {
		throw new IllegalStateException("no new instance allowed." + getClass());
	}

	public static void verify(final Class<?> clazz) {
		 getInstance(clazz).verify();
	}

	public static SingleTypeEqualsVerifierApi<?> getInstance(final Class<?> clazz) {
		return EqualsVerifier.forClass(clazz)
			.usingGetClass()
			.suppress(Warning.STRICT_INHERITANCE)
			.suppress(Warning.NONFINAL_FIELDS);
	}

	public static void verifyEntity(
		final Class<?> clazz) {
		getInstance(clazz)
			.withIgnoredAnnotations(javax.persistence.Id.class)
			.verify();
	}

}
