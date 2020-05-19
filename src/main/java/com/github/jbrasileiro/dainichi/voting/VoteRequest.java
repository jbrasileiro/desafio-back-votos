package com.github.jbrasileiro.dainichi.voting;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public final class VoteRequest
	implements
	Serializable {

	@NotNull
	@JsonProperty("session_id")
	private Integer session;
	@NotNull
	@JsonProperty("associate_id")
	private Integer associate;
	@NotNull
	private Boolean vote;
}
