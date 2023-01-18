package com.xdesign.hackronym.slash.acronym;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.retriever.AcronymRetriever;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcronymGetCommandTest {

	private AcronymGetCommand acronymCommand;

	@Mock
	private AcronymRetriever acronymRetriever;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	@BeforeEach
	public void setup() {
		acronymCommand = new AcronymGetCommand( acronymRetriever );

		when( acronymRetriever.getAcronym( "ASAP" ) ).thenReturn( Acronym.builder()
				.acronym( "ASAP" )
				.meaning( "As soon as possible" )
				.description( "Common desc" )
				.build()
				.toString() );
	}

	@Test
	public void shouldCallAcronymRetriever() {
		final Response response = acronymCommand.doRespond( "ASAP", slashCommandRequest,
				slashCommandContext );

		verify( acronymRetriever ).getAcronym( "ASAP" );
	}

	@Test
	public void shouldReturnNiceMessageIfNotFound() {

		when( acronymRetriever.getAcronym( "ASAP" ) )
				.thenReturn( "No acronym found - you can add a new one though using /addacronym" );

		final Response response = acronymCommand.doRespond( "ASAP", slashCommandRequest,
				slashCommandContext );

		verify( slashCommandContext ).ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( "No acronym found - you can add a new one though using /addacronym" )
				.build() );
	}
}
