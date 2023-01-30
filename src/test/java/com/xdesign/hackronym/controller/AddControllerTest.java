package com.xdesign.hackronym.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xdesign.hackronym.domain.AcronymMessage;
import com.xdesign.hackronym.store.AcronymStorer;

@WebMvcTest(AddController.class)
public class AddControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AcronymStorer acronymStorer;

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

	@Test
	void shouldAddAcronym() throws Exception {

		final AcronymMessage acronymMessage = AcronymMessage.builder()
				.acronymMessage( "ABC,A Big Chip,A short description" )
				.build();

		when( acronymStorer.storeAcronym( acronymMessage.getAcronymMessage() ) )
				.thenReturn( "Success" );

		this.mockMvc
				.perform( post( "/addacronym" ).content( asJsonString( acronymMessage ) )
						.contentType( MediaType.APPLICATION_JSON )
						.accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() );

		verify( acronymStorer, times( 1 ) ).storeAcronym( acronymMessage.getAcronymMessage() );
	}
}
