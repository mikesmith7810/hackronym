package com.xdesign.hackronym.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Contents;
import com.xdesign.hackronym.domain.Example;

@WebMvcTest(ContentsController.class)
class ContentsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContentsStore contentsStore;

	public static String asJsonString( final Object obj ) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString( obj );
	}

	@Test
	void shouldRetrieveAvailableCodeExamples() throws Exception {

		final Contents contents = Contents.builder()
				.chapters( ImmutableList.of( Chapter.builder()
						.examples( ImmutableList.of( Example.builder()
								.name( "Functional Interfaces" )
								.description( "Some examples" )
								.apiCall( "/api/func/demo" )
								.build() ) )
						.build() ) )
				.build();

		when( contentsStore.retrieveContents() ).thenReturn( contents );

		this.mockMvc.perform( get( "/java/contents" ).accept( MediaType.APPLICATION_JSON ) )
				.andDo( print() )
				.andExpect( status().isOk() )
				.andExpect( jsonPath( "$.chapters" ).exists() );

		verify( contentsStore, times( 1 ) ).retrieveContents();
	}
}
