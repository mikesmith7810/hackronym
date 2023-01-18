package com.xdesign.hackronym.store;

import static org.mockito.Mockito.verify;

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
public class AcronymStorerTest {
	private AcronymStorer acroynmStorer;

	@Mock
	private AcronymRepository acronymRepository;

	@BeforeEach
	public void setup() {
		this.acroynmStorer = new AcronymStorer( acronymRepository );
	}

	@Test
	public void shouldStoreThePassedAcronym() {
		Acronym acronym = Acronym.builder()
				.acronym( "MAD" )
				.meaning( "Magic and Dragons" )
				.description( "Something mystifying" )
				.build();

		acroynmStorer.storeAcronym( acronym );

		verify( acronymRepository ).save( acronym );
	}
}
