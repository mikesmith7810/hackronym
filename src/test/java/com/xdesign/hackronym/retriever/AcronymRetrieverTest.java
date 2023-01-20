package com.xdesign.hackronym.retriever;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.hackronym.db.AcronymRepository;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymRetrieverTest {

	private AcronymRetriever acronymRetriever;

	@Mock
	private AcronymRepository acronymRepository;

	@BeforeEach
	public void setup() {
		this.acronymRetriever = new AcronymRetriever( acronymRepository );
	}

	@Test
	void shoudlCallOutToAcronymRepository() {
		acronymRetriever.getAcronym( "ASAP" );

		verify( acronymRepository ).getByAcronym( "ASAP" );
	}

	@Test
	void shoudlGetAllAcronyms() {
		acronymRetriever.getAll();

		verify( acronymRepository ).findAll();
	}

	@Test
	void shouldReturnNiceMessageIfNoAcronymFound() {
		when( acronymRepository.getByAcronym( "ASAP" ) ).thenReturn( null );

		final String message = acronymRetriever.getAcronym( "ASAP" );

		assertThat( message )
				.isEqualTo( "No acronym found - you can add a new one though using /addacronym" );
	}
}
