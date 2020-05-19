package com.github.jbrasileiro.dainichi.voting;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.jbrasileiro.dainichi.associate.AssociateService;
import com.github.jbrasileiro.dainichi.commons.AbstractService;
import com.github.jbrasileiro.dainichi.commons.ApplicationRuntimeException;
import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;
import com.github.jbrasileiro.dainichi.commons.service.DateProvider;
import com.github.jbrasileiro.dainichi.orm.entity.Associate;
import com.github.jbrasileiro.dainichi.orm.entity.Session;
import com.github.jbrasileiro.dainichi.orm.entity.Voting;
import com.github.jbrasileiro.dainichi.orm.repository.VotingRepository;
import com.github.jbrasileiro.dainichi.session.SessionService;

@Service
public final class VotingServiceDefault
	extends
	AbstractService<VotingRepository, Voting, Integer>
	implements
	VotingService {

	private final DateProvider dateProvider;
	private final SessionService sessionService;
	private final AssociateService associateService;

	@Autowired
	public VotingServiceDefault(
		final DateProvider dateProvider,
		final SessionService sessionService,
		final AssociateService associateService,
		final VotingRepository repository) {
		super(repository);
		this.dateProvider = dateProvider;
		this.sessionService = sessionService;
		this.associateService = associateService;
	}

	@Override
	public Voting vote(
		final VoteRequest request) {
		Integer idSession = request.getSession();
		Integer idAssociate = request.getAssociate();
		Session session = sessionService.findByID(idSession);

		LocalDateTime now = dateProvider.nowLocalDateTime();
		LocalDateTime sessionEndTime = session.getTimeEnd();
		if(now.isAfter(sessionEndTime)) {
			throw new ApplicationRuntimeException("Sessing is closed");
		}

		Associate associate = associateService.validateAssociateVote(idAssociate);

		Voting voting = new Voting();
		voting.setSession(session);
		voting.setAssociate(associate);
		voting.setVote(VoteEnum.valueFrom(request.getVote()));
		return save(voting);
	}

	@Override
	public Collection<VoteEnum> findResultVotingBySession(
		final Integer idSession) {
		return getRepository().findResultVotingBySession(idSession);
	}

}
