package com.github.jbrasileiro.testing;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UTRepository
	extends
	JpaRepository<UTEntity, Integer> {
}
