package com.github.jbrasileiro.dainichi.session;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.jbrasileiro.dainichi.commons.service.DateProvider;
import com.github.jbrasileiro.dainichi.orm.entity.Session;
import com.github.jbrasileiro.dainichi.orm.repository.SessionRepository;
import com.github.jbrasileiro.dainichi.ruling.RulingService;

class SessionServiceDefaultTest {

	private static final LocalDateTime NOW = LocalDateTime.of(2000, 12, 31, 23, 59, 59, 0);
	private static final Long ONE_MINUTE = 1L;
	private SessionService service;
	@Mock
	private SessionRepository repository;
	@Mock
	private RulingService rulingService;
	@Mock
	private DateProvider dateProvider;

	@BeforeEach
	void setUp()
		throws Exception {
		MockitoAnnotations.initMocks(this);
		service = new SessionServiceDefault(repository, rulingService, dateProvider);
	}

	@Test
	void openSessionWithoutDuration() {
		SessionOpenRequest request = SessionOpenRequest.builder()
				.idRulling(10)
				.build();

		Session expected = new Session();
		expected.setTimeStart(NOW);
		expected.setTimeEnd(LocalDateTime.of(2001, 01, 1, 00, 00, 59, 0));
		expected.setDuration(ONE_MINUTE);

		Mockito.when(dateProvider.nowLocalDateTime()).thenReturn(NOW);

		service.openSession(request);

		Mockito.verify(rulingService).findByID(ArgumentMatchers.eq(10));
		Mockito.verify(dateProvider).nowLocalDateTime();
		Mockito.verify(repository).save(ArgumentMatchers.eq(expected));
	}

	@Test
	void openSessionWithZeroDuration() {
		SessionOpenRequest request = SessionOpenRequest.builder()
				.idRulling(20)
				.duration(0L)
				.build();

		Session expected = new Session();
		expected.setTimeStart(NOW);
		expected.setTimeEnd(LocalDateTime.of(2001, 01, 1, 00, 00, 59, 0));
		expected.setDuration(ONE_MINUTE);

		Mockito.when(dateProvider.nowLocalDateTime()).thenReturn(NOW);

		service.openSession(request);

		Mockito.verify(rulingService).findByID(ArgumentMatchers.eq(20));
		Mockito.verify(dateProvider).nowLocalDateTime();
		Mockito.verify(repository).save(ArgumentMatchers.eq(expected));
	}

	@Test
	void openSessionWithDuration() {
		SessionOpenRequest request = SessionOpenRequest.builder()
				.idRulling(30)
				.duration(60L)
				.build();

		Session expected = new Session();
		expected.setTimeStart(NOW);
		expected.setTimeEnd(LocalDateTime.of(2001, 01, 1, 00, 59, 59, 0));
		expected.setDuration(60L);

		Mockito.when(dateProvider.nowLocalDateTime()).thenReturn(NOW);

		service.openSession(request);

		Mockito.verify(rulingService).findByID(ArgumentMatchers.eq(30));
		Mockito.verify(dateProvider).nowLocalDateTime();
		Mockito.verify(repository).save(ArgumentMatchers.eq(expected));
	}
}
