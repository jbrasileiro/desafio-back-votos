package com.github.jbrasileiro.dainichi.session;

import java.util.Collection;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;
import com.github.jbrasileiro.dainichi.commons.to.VotingResultTO;
import com.github.jbrasileiro.dainichi.orm.entity.Session;
import com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream.SessionResultOuputSource;
import com.github.jbrasileiro.dainichi.voting.VotingService;


@Service
public final class SessionResultServiceDefault
	implements
	SessionResultService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SessionResultServiceDefault.class);

	private final SessionService sessionService;
	private final VotingService votingService;

	private final SessionResultOuputSource source;

	@Autowired
	public SessionResultServiceDefault(
		final SessionService sessionService,
		final VotingService votingService,
		final SessionResultOuputSource source) {
		super();
		this.sessionService = sessionService;
		this.votingService = votingService;
		this.source = source;
	}

	@Override
	public VotingResultTO findResultVotingBySession(
		final Integer idSession) {
		Collection<VoteEnum> values = votingService.findResultVotingBySession(idSession);
		long totalYes = values.stream().filter(VoteEnum.YES::equals).count();
		long totalNo = values.stream().filter(VoteEnum.NO::equals).count();
		return VotingResultTO.builder()
				.idSession(idSession)
				.total(Long.valueOf(values.size()))
				.totalYes(totalYes)
				.totalNo(totalNo)
				.build();
	}

	private Collection<Session> findSessionClosed() {
		return sessionService.findSessionClosed();
	}

	@Override
	public void updateSessionClosed() {
		Collection<Session> sessions = findSessionClosed();
		if (CollectionUtils.isNotEmpty(sessions)) {
			LOGGER.info("update sessions result {}", sessions.stream()
				.map(each -> String.valueOf(each.getId()))
				.collect(Collectors.joining(",", "{", "}")));
			for (Session session : sessions) {
				Integer sessionId = session.getId();
				VotingResultTO result = findResultVotingBySession(sessionId);
				send(result);
				session.setSend(Boolean.TRUE);
				sessionService.save(session);
			}
		}
	}

	private void send(
		final VotingResultTO result) {
		try {
			Message<?> message = MessageBuilder.withPayload(result).build();
			source.channel().send(message);
			Integer idSession = result.getIdSession();
			LOGGER.info("JMS message send for session {}", idSession);
		} catch (Exception e) {
			LOGGER.error("could not send message", e);
		}
	}
}
