package com.github.jbrasileiro.testing;

import org.mockito.ArgumentMatcher;
import org.springframework.messaging.Message;

public final class MessageBodyMatcher<T>
	implements
	ArgumentMatcher<Message<T>> {

	private final T expectedBody;

	public MessageBodyMatcher(
		final T expectedBody) {
		super();
		this.expectedBody = expectedBody;
	}

	@Override
	public boolean matches(
		final Message<T> current) {
		return expectedBody.equals(current.getPayload());
	}

	@Override
	public String toString() {
		return "expected: " + expectedBody.toString();
	}
}
