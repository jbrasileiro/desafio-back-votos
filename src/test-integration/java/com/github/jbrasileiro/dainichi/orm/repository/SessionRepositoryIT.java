package com.github.jbrasileiro.dainichi.orm.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.jbrasileiro.dainichi.TemplateIT;
import com.github.jbrasileiro.dainichi.orm.entity.Ruling;
import com.github.jbrasileiro.dainichi.orm.entity.Session;

public class SessionRepositoryIT
	extends
	TemplateIT {

	@Autowired
	private RulingRepository ruleRepository;
	@Autowired
	private SessionRepository sessionRepository;

	/**
	 * Just an example to demonstrate we can create IT for repository	\\UNNECESSARY_COMMENT
	 */
	@Test
	void findByTimeEndBeforeAndSendFalseOrSendIsNull() {
		//Arrange - DB - starting \\UNNECESSARY_COMMENT
		Ruling ruling = new Ruling();
		ruling.setName("Rule 01");
		ruleRepository.save(ruling);

		LocalDateTime timeStart = LocalDateTime.of(2000, 1, 1, 0, 0, 0, 0);
		LocalDateTime timeEnd = LocalDateTime.of(2001, 1, 1, 0, 0, 0, 0);
		Session session = new Session();
		session.setTimeStart(timeStart);
		session.setTimeEnd(timeEnd);
		session.setDuration(10000L);
		session.setRuling(ruling);
		sessionRepository.save(session);

		Session expectedSession = new Session();
		expectedSession.setId(1);
		expectedSession.setTimeStart(timeStart);
		expectedSession.setTimeEnd(timeEnd);
		expectedSession.setDuration(10000L);
		expectedSession.setRuling(ruling);
		sessionRepository.save(expectedSession);

		Collection<Session> result = sessionRepository.findByTimeEndBeforeAndSendFalseOrSendIsNull(LocalDateTime.now());
		Assert.assertTrue(result.contains(expectedSession));
	}
}
