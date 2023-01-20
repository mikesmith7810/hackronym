package com.xdesign.hackronym.slash.acronym;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.retriever.AcronymRetriever;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymGetAllCommandTest {

	private AcronymGetAllCommand acronymGetAllCommand;

	@Mock
	private AcronymRetriever acronymRetriever;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	@BeforeEach
	public void setup() {
		acronymGetAllCommand = new AcronymGetAllCommand( acronymRetriever );

		when( acronymRetriever.getAll( ) ).thenReturn(  ImmutableList.of( Acronym.builder().acronym( "ASSP" ).build(),
				Acronym.builder().acronym( "TEST" ).build() ));
	}

	@Test
	void shouldCallAcronymRetriever() {
		final Response response = acronymGetAllCommand.doRespond( "", slashCommandRequest,
				slashCommandContext );

		verify( acronymRetriever ).getAll();
	}


}
