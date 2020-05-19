package com.github.jbrasileiro.dainichi.orm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;

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
	name = "VOTING",
	uniqueConstraints = {
		@UniqueConstraint(
			columnNames = {
				"ID_ASSOCIATE",
				"ID_SESSION"
			})
	})
public final class Voting {

	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(
		optional = false)
	@JoinColumn(
		name = "ID_ASSOCIATE")
	private Associate associate;

	@ManyToOne(
		optional = false)
	@JoinColumn(
		name = "ID_SESSION")
	private Session session;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private VoteEnum vote;

}
