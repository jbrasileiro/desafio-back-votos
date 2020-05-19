package com.github.jbrasileiro.dainichi.voting;

import java.util.Collection;

import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;
import com.github.jbrasileiro.dainichi.orm.entity.Voting;

public interface VotingService {

	Collection<Voting> findAll();

	Voting findByID(
		Integer id);

	Voting vote(
		VoteRequest request);

	Collection<VoteEnum> findResultVotingBySession(
		Integer idSession);

}
