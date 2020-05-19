package com.github.jbrasileiro.dainichi.commons.service;

import java.time.LocalDateTime;
import java.util.Date;

public interface DateProvider {

	Date now();

	LocalDateTime nowLocalDateTime();
}
