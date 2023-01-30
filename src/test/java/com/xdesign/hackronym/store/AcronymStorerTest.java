package com.xdesign.hackronym.store;

import static org.mockito.Mockito.never;
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
import com.xdesign.hackronym.retriever.AcronymRetriever;
import com.xdesign.hackronym.slash.acronym.parser.AcronymParser;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AcronymStorerTest {
	private AcronymStorer acroynmStorer;

	@Mock
	private AcronymRepository acronymRepository;

	@Mock
	AcronymParser acronymParser;

	@Mock
	AcronymRetriever acronymRetriever;

	Acronym acronym;

	@BeforeEach
	public void setup() {
		this.acroynmStorer = new AcronymStorer( acronymRepository,
				acronymParser,
				acronymRetriever );

		acronym = Acronym.builder()
				.acronym( "MAD" )
				.meaning( "Magic and Dragons" )
				.description( "Something mystifying" )
				.build();
	}

	@Test
	public void shouldStoreThePassedAcronym() {

		when( acronymParser.parse( "MAD,Magic and Dragons,Something mystifying" ) )
				.thenReturn( Optional.of(acronym) );
		when( acronymRetriever.getAcronym( acronym.getAcronym() ) ).thenReturn( "No Result Found" );

		acroynmStorer.storeAcronym( "MAD,Magic and Dragons,Something mystifying" );

		verify( acronymParser ).parse( "MAD,Magic and Dragons,Something mystifying" );
		verify( acronymRetriever ).getAcronym( acronym.getAcronym() );
		verify( acronymRepository ).save( acronym );

	}

	@Test
	void shouldNotStoreAcronymIfAcronymAlreadyExists() {

		when( acronymParser.parse( "MAD,Magic and Dragons,Something mystifying" ) )
				.thenReturn( Optional.of(acronym) );
		when( acronymRetriever.getAcronym( acronym.getAcronym() ) )
				.thenReturn( "MAD,Magic and Dragons,Something mystifying" );

		acroynmStorer.storeAcronym( "MAD,Magic and Dragons,Something mystifying" );

		verify( acronymRepository, never() ).save( acronym );
	}

	@Test
	void shouldNotStoreAcronymIfAcronymIsNotValid() {

		when( acronymParser.parse( "Test,Magic and Dragons,Something mystifying" ) )
				.thenReturn( Optional.empty() );

		acroynmStorer.storeAcronym( "Test,Magic and Dragons,Something mystifying" );

		verify( acronymRepository, never() ).save( acronym );
	}
}
