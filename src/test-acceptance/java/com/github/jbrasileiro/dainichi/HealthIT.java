package com.github.jbrasileiro.dainichi;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@Ignore
public class HealthIT
	extends
	TemplateFT {

	@Autowired
    private MockMvc mvc;

	@Test
	void requestRentBookAvaliable() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/actuator/health"))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(contentJSON("{\"status\":\"UP\"}"));
	}

}
