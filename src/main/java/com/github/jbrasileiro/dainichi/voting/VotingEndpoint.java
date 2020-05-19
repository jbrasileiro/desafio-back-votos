package com.github.jbrasileiro.dainichi.voting;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.jbrasileiro.dainichi.orm.entity.Voting;

@RestController
@RequestMapping("/vote")
public class VotingEndpoint {

	private final VotingService votingService;

	@Autowired
	public VotingEndpoint(
		final VotingService votingService) {
		super();
		this.votingService = votingService;
	}

	@GetMapping
	public Collection<Voting> all() {
		return votingService.findAll();
	}

	@PostMapping
	public Voting vote(
		@Valid @RequestBody final VoteRequest request) {
		return votingService.vote(request);
	}
}
