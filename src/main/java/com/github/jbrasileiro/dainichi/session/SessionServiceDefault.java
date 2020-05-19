package com.github.jbrasileiro.dainichi.session;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbrasileiro.dainichi.commons.AbstractService;
import com.github.jbrasileiro.dainichi.commons.service.DateProvider;
import com.github.jbrasileiro.dainichi.orm.entity.Ruling;
import com.github.jbrasileiro.dainichi.orm.entity.Session;
import com.github.jbrasileiro.dainichi.orm.repository.SessionRepository;
import com.github.jbrasileiro.dainichi.ruling.RulingService;

@Service
public final class SessionServiceDefault
	extends
	AbstractService<SessionRepository, Session, Integer>
	implements
	SessionService {

	private static final long ONE_MINUTE = 1L;

	private final RulingService rulingService;
	private final DateProvider dateProvider;

	@Autowired
	public SessionServiceDefault(
		final SessionRepository repository,
		final RulingService rulingService,
		final DateProvider dateProvider) {
		super(repository);
		this.rulingService = rulingService;
		this.dateProvider = dateProvider;
	}

	@Override
	public Session save(
		final Session entity) {
		return super.save(entity);
	}

	@Override
	public Session openSession(
		final SessionOpenRequest request) {
		Integer idRulling = request.getIdRulling();
		Ruling ruling = findRulling(idRulling);
		LocalDateTime nowLocalDateTime = dateProvider.nowLocalDateTime();
		Session session = new Session();
		session.setRuling(ruling);
		session.setTimeStart(nowLocalDateTime);
		Long duration = request.getDuration();
		if (Objects.isNull(duration) || 0 >= duration.longValue()) {
			session.setDuration(ONE_MINUTE);
		} else {
			session.setDuration(duration);
		}
		session.updateSessionTimeEnd();
		return save(session);
	}

	private Ruling findRulling(
		final Integer idRulling) {
		return rulingService.findByID(idRulling);
	}

	@Override
	public Session findSessionByRuling(
		final Integer idRulling) {
		return getRepository().findByIdRulling(idRulling);
	}

	@Override
	public Collection<Session> findSessionClosed() {
		LocalDateTime now = dateProvider.nowLocalDateTime();
		return getRepository().findByTimeEndBeforeAndSendFalseOrSendIsNull(now);
	}

}
