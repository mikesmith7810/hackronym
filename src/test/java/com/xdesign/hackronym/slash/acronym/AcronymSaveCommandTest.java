package com.xdesign.hackronym.slash.acronym;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.xdesign.hackronym.slash.acronym.parser.AcronymParser;
import com.xdesign.hackronym.slash.acronym.validator.AcronymValidator;
import com.xdesign.hackronym.store.AcronymStorer;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcronymSaveCommandTest {

	private AcronymSaveCommand acronymSaveCommand;

	@Mock
	private AcronymStorer acronymStorer;

	@Mock
	AcronymValidator acronymValidator;

	@Mock
	AcronymParser acronymParser;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	@BeforeEach
	public void setup() {
		acronymSaveCommand = new AcronymSaveCommand( acronymStorer,
				acronymValidator,
				acronymParser );

		Acronym acronym = Acronym.builder()
				.acronym( "ASAP" )
				.meaning( "As soon as possible" )
				.description( "Very quickly" )
				.build();

		when( acronymValidator.isValid( "ASAP,As soon as possible,Very quickly" ) )
				.thenReturn( true );
		when( acronymParser.parse( "ASAP,As soon as possible,Very quickly" ) )
				.thenReturn( acronym );
		when( acronymStorer.storeAcronym( acronym ) ).thenReturn( acronym );

	}

	@Test
	public void shouldCallAcronymRetriever() {
		final Response response = acronymSaveCommand.doRespond(
				"ASAP,As soon as possible,Very quickly", slashCommandRequest, slashCommandContext );

		verify( acronymStorer ).storeAcronym( Acronym.builder()
				.acronym( "ASAP" )
				.meaning( "As soon as possible" )
				.description( "Very quickly" )
				.build() );
	}

}
