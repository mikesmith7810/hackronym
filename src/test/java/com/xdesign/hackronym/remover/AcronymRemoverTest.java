package com.xdesign.hackronym.remover;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.hackronym.db.AcronymRepository;
import com.xdesign.hackronym.domain.Acronym;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymRemoverTest {

	private AcronymRemover acronymRemover;

	@Mock
	private AcronymRepository acronymRepository;

	private Acronym acronym;

	@BeforeEach
	public void setup() {
		this.acronymRemover = new AcronymRemover( acronymRepository );

		acronym = Acronym.builder()
				.acronym( "ASAP" )
				.meaning( "As Soon As Possibler" )
				.description( "Pretty quick" )
				.build();
	}

	@Test
	void shouldRemoveAcronym() {
		when( acronymRepository.getByAcronym( "ASAP" ) ).thenReturn( acronym );

		acronymRemover.removeAcronym( "ASAP" );

		verify( acronymRepository ).delete( acronym );
	}

}
