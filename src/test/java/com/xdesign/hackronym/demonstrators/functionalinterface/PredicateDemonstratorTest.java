package com.xdesign.hackronym.demonstrators.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PredicateDemonstratorTest {

	private PredicateDemonstrator predicateDemonstrator;

	@BeforeAll
	public void setup() {
		predicateDemonstrator = new PredicateDemonstrator();
	}

	@Test
	public void shouldCheckIfWordsStartsWithS() {

		final boolean result = predicateDemonstrator.runExampleFor( ImmutableList.of( "super" ) );

		assertThat( result ).isTrue();
	}

	@Test
	public void shouldFilterListOfWords() {

		final List<String> words = ImmutableList.of( "Song", "Stack", "Car", "Engine", "Strum" );

		final List<String> filteredWords = predicateDemonstrator.runExampleFor( "S", words );

		assertThat( filteredWords ).hasSize( 3 );
		assertThat( filteredWords ).filteredOn( word -> word.startsWith( "S" ) );
	}
}
