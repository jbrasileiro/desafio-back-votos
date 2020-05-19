package com.github.jbrasileiro.dainichi.sessionresult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbrasileiro.dainichi.commons.to.VotingResultTO;
import com.github.jbrasileiro.dainichi.session.SessionResultService;

@RestController
@RequestMapping("/session/result")
public class SessionResultEndpoint {

	private final SessionResultService service;

	@Autowired
	public SessionResultEndpoint(
		final SessionResultService service) {
		super();
		this.service = service;
	}

	@GetMapping("/{idSession}")
	public VotingResultTO result(
		@PathVariable("idSession") final Integer idSession) {
		return service.findResultVotingBySession(idSession);
	}
}
