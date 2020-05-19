package com.github.jbrasileiro.dainichi.commons.to;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public final class VotingResultTO
	implements
	Serializable {

	private Integer idSession;
	@JsonProperty("total")
	private Long total;
	@JsonProperty("yes")
	private Long totalYes;
	@JsonProperty("no")
	private Long totalNo;
}
