package com.github.jbrasileiro.dainichi.sessionresult.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.jbrasileiro.dainichi.session.SessionResultService;

@Component
public class SessionClosedJob
	implements
	Job {

	private final SessionResultService service;

	@Autowired
	public SessionClosedJob(
		final SessionResultService service) {
		super();
		this.service = service;
	}

	@Override
	public void execute(
		final JobExecutionContext context)
		throws JobExecutionException {
		service.updateSessionClosed();
	}
}
