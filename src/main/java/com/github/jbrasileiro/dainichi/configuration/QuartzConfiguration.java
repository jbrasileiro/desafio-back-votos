package com.github.jbrasileiro.dainichi.configuration;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.github.jbrasileiro.dainichi.sessionresult.job.SessionClosedJob;

@PropertySource("classpath:/properties/quartz.properties")
@Configuration
public class QuartzConfiguration {

	@Bean
	public JobDetail jobADetails() {
		return JobBuilder.newJob(SessionClosedJob.class)
				.withIdentity("job-session-closed")
				.storeDurably()
				.build();
	}

	@Bean
	public Trigger jobATrigger(
		final JobDetail job) {
		return TriggerBuilder.newTrigger()
				.forJob(job)
				.withIdentity("trigger")
				.withSchedule(CronScheduleBuilder.cronSchedule("5 * * ? * * *"))
				.build();
	}
}
