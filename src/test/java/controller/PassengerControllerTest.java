package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.TestWebappConfig;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ContextConfiguration(classes = {TestWebappConfig.class})
@WebAppConfiguration
public class PassengerControllerTest extends AbstractTestNGSpringContextTests{

	@Autowired
	private WebApplicationContext applicationContext;
	private MockMvc mockMvc;

	@BeforeMethod
	public void setUp() throws Exception {

		mockMvc = MockMvcBuilders.webAppContextSetup(applicationContext).build();
	}

	@Test
	public void passengersReturnListOfAllPassengers() throws Exception {
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/passengers").accept(MediaType.ALL);

		ResultActions result = mockMvc.perform(request);
		String content = result
				.andExpect(status().isOk())
				.andReturn()
				.getResponse()
				.getContentAsString();

		assertEquals("[]",content);
	}
}
