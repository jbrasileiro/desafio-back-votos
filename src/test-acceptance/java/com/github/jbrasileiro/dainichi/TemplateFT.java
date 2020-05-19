package com.github.jbrasileiro.dainichi;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Template for integration test class (System level/White-box)
 */
@ActiveProfiles("AT")
public class TemplateFT
	extends
	TemplateIT {

	protected ResultMatcher contentJSON(
		final String content) {
		return MockMvcResultMatchers.content().json(content, true);
	}
}
