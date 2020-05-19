package com.github.jbrasileiro.dainichi.session;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

import com.github.jbrasileiro.dainichi.commons.enums.VoteEnum;
import com.github.jbrasileiro.dainichi.commons.to.VotingResultTO;
import com.github.jbrasileiro.dainichi.orm.entity.Session;
import com.github.jbrasileiro.dainichi.sessionresult.jms.cloudstream.SessionResultOuputSource;
import com.github.jbrasileiro.dainichi.voting.VotingService;
import com.github.jbrasileiro.testing.MessageBodyMatcher;


class SessionResultServiceDefaultTest {

	@Mock
	private SessionService sessionService;
	@Mock
	private VotingService votingService;
	@Mock
	private SessionResultOuputSource source;
	@Mock
	private MessageChannel channel;
	private SessionResultServiceDefault service;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		Mockito.when(source.channel()).thenReturn(channel);
		service = new SessionResultServiceDefault(sessionService, votingService, source);

	}

	@Test
	void testFindResultVotingBySessionNoVotes() {
		VotingResultTO expected = VotingResultTO.builder()
				.idSession(10)
				.total(0L)
				.totalNo(0L)
				.totalYes(0L)
				.build();
		Mockito.when(votingService.findResultVotingBySession(ArgumentMatchers.anyInt()))
			.thenReturn(Collections.emptyList());
		VotingResultTO result = service.findResultVotingBySession(10);
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testFindResultVotingBySessionOnlyYesVotes() {
		VotingResultTO expected = VotingResultTO.builder()
				.idSession(20)
				.total(1L)
				.totalNo(0L)
				.totalYes(1L)
				.build();
		List<VoteEnum> returnVotes = Arrays.asList(VoteEnum.YES);
		Mockito.when(votingService.findResultVotingBySession(ArgumentMatchers.anyInt()))
			.thenReturn(returnVotes);
		VotingResultTO result = service.findResultVotingBySession(20);
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testFindResultVotingBySessionOnlyNoVotes() {
		VotingResultTO expected = VotingResultTO.builder()
				.idSession(30)
				.total(1L)
				.totalNo(1L)
				.totalYes(0L)
				.build();
		List<VoteEnum> returnVotes = Arrays.asList(VoteEnum.NO);
		Mockito.when(votingService.findResultVotingBySession(ArgumentMatchers.anyInt()))
			.thenReturn(returnVotes);
		VotingResultTO result = service.findResultVotingBySession(30);
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testFindResultVotingBySessionBothVotes() {
		VotingResultTO expected = VotingResultTO.builder()
				.idSession(40)
				.total(2L)
				.totalNo(1L)
				.totalYes(1L)
				.build();
		List<VoteEnum> returnVotes = Arrays.asList(VoteEnum.YES, VoteEnum.NO);
		Mockito.when(votingService.findResultVotingBySession(ArgumentMatchers.anyInt()))
			.thenReturn(returnVotes);
		VotingResultTO result = service.findResultVotingBySession(40);
		Assertions.assertEquals(expected, result);
	}

	@Test
	void testUpdateSessionClosedEmptySessions() {
		Mockito.when(sessionService.findSessionClosed())
			.thenReturn(Collections.emptyList());
		service.updateSessionClosed();
		Mockito.verify(channel, Mockito.never()).send(ArgumentMatchers.any(Message.class));
		Mockito.verify(sessionService, Mockito.never()).save(ArgumentMatchers.any(Session.class));
	}

	@Test
	void testUpdateSessionClosedOneSessions() {
		Session session = new Session();
		session.setId(200);

		VotingResultTO expectedBody = VotingResultTO.builder()
			.idSession(200)
			.total(3L)
			.totalNo(1L)
			.totalYes(2L)
			.build();
		List<VoteEnum> returnVotes = Arrays.asList(VoteEnum.YES, VoteEnum.YES, VoteEnum.NO);

		Mockito.when(votingService.findResultVotingBySession(ArgumentMatchers.anyInt()))
			.thenReturn(returnVotes);
		Mockito.when(sessionService.findSessionClosed())
			.thenReturn(Arrays.asList(session));

		service.updateSessionClosed();

		Mockito.verify(channel, Mockito.times(1))
			.send(ArgumentMatchers.argThat(new MessageBodyMatcher<>(expectedBody)));
		Mockito.verify(sessionService, Mockito.times(1)).save(ArgumentMatchers.same(session));
	}
}
