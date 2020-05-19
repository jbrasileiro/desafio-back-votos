package com.github.jbrasileiro.dainichi.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(
	callSuper = true)
public class ApplicationRuntimeException
	extends
	RuntimeException {

	public ApplicationRuntimeException(
		final String message,
		final Throwable cause) {
		super(message, cause);
	}

	public ApplicationRuntimeException(
		final String message) {
		super(message);
	}

	public ApplicationRuntimeException(
		final Throwable cause) {
		super(cause);
	}

	@Override
	public final String getMessage() {
		return super.getMessage();
	}

	@Override
	public final StackTraceElement[] getStackTrace() {
		return super.getStackTrace();
	}

}
