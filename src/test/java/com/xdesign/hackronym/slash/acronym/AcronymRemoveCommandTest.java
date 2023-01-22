package com.xdesign.hackronym.slash.acronym;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.remover.AcronymRemover;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymRemoveCommandTest {

	private AcronymRemoveCommand acronymRemoveCommand;

	@Mock
	private AcronymRemover acronymRemover;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;
	private Acronym acronym;

	@BeforeEach
	public void setup() {
		acronymRemoveCommand = new AcronymRemoveCommand( acronymRemover );
	}

	@Test
	void shouldCallAcronymRemover() {

		final Response response = acronymRemoveCommand.doRespond( "ASAP", slashCommandRequest,
				slashCommandContext );

		verify( acronymRemover ).removeAcronym( "ASAP" );
	}

}
