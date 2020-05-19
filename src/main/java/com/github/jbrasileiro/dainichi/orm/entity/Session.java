package com.github.jbrasileiro.dainichi.orm.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.github.jbrasileiro.dainichi.commons.ApplicationRuntimeException;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Entity
@Table(
	name = "SESSION")
public final class Session {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY)
	private Integer id;
	@Basic
	@Column(
		nullable = false)
	private LocalDateTime timeStart;
	@Column(
		nullable = false)
	private LocalDateTime timeEnd;
	@Column(
		columnDefinition = "NUMERIC(10,0)")
	private Long duration;
	@Column
	private Boolean send = false;
	@ManyToOne(
		optional = false)
	@JoinColumn(
		name = "ID_RULING")
	private Ruling ruling;

	public void updateSessionTimeEnd() {
		if (Objects.isNull(timeStart) || Objects.isNull(duration)) {
			throw new ApplicationRuntimeException("could not update end time");
		}
		LocalDateTime date = timeStart.plusMinutes(duration);
		setTimeEnd(date);
	}
}
