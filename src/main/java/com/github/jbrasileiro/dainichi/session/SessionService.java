package com.github.jbrasileiro.dainichi.session;

import java.util.Collection;

import com.github.jbrasileiro.dainichi.orm.entity.Session;

public interface SessionService {

	Collection<Session> findAll();

	Session openSession(
		SessionOpenRequest request);

	Session findSessionByRuling(
		Integer idRulling);

	Session findByID(
		Integer idSession);

	Collection<Session> findSessionClosed();

	Session save(
		Session session);

}
