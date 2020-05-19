package com.github.jbrasileiro.dainichi.commons.service;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public final class DefaultDateProvider
	implements
	DateProvider {

	@Override
	public Date now() {
		return new Date();
	}

	@Override
	public LocalDateTime nowLocalDateTime() {
		return LocalDateTime.now();
	}
}
