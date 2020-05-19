package com.github.jbrasileiro.dainichi.session;

import com.github.jbrasileiro.dainichi.commons.to.VotingResultTO;

public interface SessionResultService {

	VotingResultTO findResultVotingBySession(
		Integer idSession);

	void updateSessionClosed();
}
