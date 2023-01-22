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
import com.xdesign.hackronym.store.AcronymStorer;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymSaveCommandTest {

	private AcronymSaveCommand acronymSaveCommand;

	@Mock
	private AcronymStorer acronymStorer;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;
	private Acronym acronym;

	@BeforeEach
	public void setup() {
		acronymSaveCommand = new AcronymSaveCommand( acronymStorer );

		acronym = Acronym.builder()
				.acronym( "ASAP" )
				.meaning( "As soon as possible" )
				.description( "Very quickly" )
				.build();
	}

	@Test
	void shouldCallAcronymStorer() {
		final Response response = acronymSaveCommand.doRespond(
				"ASAP,As Soon As Possible,Pretty Quick", slashCommandRequest, slashCommandContext );

		verify( acronymStorer ).storeAcronym( "ASAP,As Soon As Possible,Pretty Quick" );
	}

}
