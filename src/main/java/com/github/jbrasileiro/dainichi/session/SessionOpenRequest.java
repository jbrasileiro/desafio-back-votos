package com.github.jbrasileiro.dainichi.session;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SessionOpenRequest
	implements
	Serializable {

	@NotNull
	private Integer idRulling;
	@Min(
		value = 0)
	@Max(
		value = 9999999999L)
	private Long duration;
}
