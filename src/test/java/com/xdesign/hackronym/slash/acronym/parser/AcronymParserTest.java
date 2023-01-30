package com.xdesign.hackronym.slash.acronym.parser;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AcronymParserTest {
	private AcronymParser acronymParser;

	@BeforeEach
	public void setup() {
		acronymParser = new AcronymParser();
	}

	@Test
	void shouldReturnAnAcronymFromSuppliedMessage() {
		final var acronymOpt = acronymParser.parse( "ASAP,As soon as,very quickly" );

		assertThat(acronymOpt).isPresent();

		final var acronym = acronymOpt.get();

		assertThat( acronym.getAcronym() ).isEqualTo( "ASAP" );
		assertThat( acronym.getMeaning() ).isEqualTo( "As soon as" );
		assertThat( acronym.getDescription() ).isEqualTo( "very quickly" );
	}

	@Test
	void shouldReturnAnAcronymFromSuppliedMessageWithCommasInDescription() {
		final var acronymOpt = acronymParser.parse( "ASAP,As soon as,very quickly, or whatever" );

		assertThat(acronymOpt).isPresent();

		final var acronym = acronymOpt.get();

		assertThat( acronym.getAcronym() ).isEqualTo( "ASAP" );
		assertThat( acronym.getMeaning() ).isEqualTo( "As soon as" );
		assertThat( acronym.getDescription() ).isEqualTo( "very quickly, or whatever" );
	}

	@Test
	void shouldSupportAlternativeSeparator() {
		final var acronymOpt = acronymParser.parse( "ASAP|As soon as|very quickly" );

		assertThat(acronymOpt).isPresent();

		final var acronym = acronymOpt.get();

		assertThat( acronym.getAcronym() ).isEqualTo( "ASAP" );
		assertThat( acronym.getMeaning() ).isEqualTo( "As soon as" );
		assertThat( acronym.getDescription() ).isEqualTo( "very quickly" );
	}

	@Test
	void shouldSupportCommasInDescription() {
		final var acronymOpt = acronymParser.parse("PICNIC|Problem In Chair, Not In Computer|Indicates that I/we/you/they caused the problem, and the software is fine. .");

		assertThat(acronymOpt).isPresent();

		final var acronym = acronymOpt.get();

		assertThat( acronym.getAcronym() ).isEqualTo( "PICNIC" );
		assertThat( acronym.getMeaning() ).isEqualTo( "Problem In Chair, Not In Computer" );
		assertThat( acronym.getDescription() ).isEqualTo( "Indicates that I/we/you/they caused the problem, and the software is fine. ." );
	}
}
