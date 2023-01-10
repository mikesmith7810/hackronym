package com.xdesign.hackronym.demonstrators.streams;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ForEachDemonstratorTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	private ForEachDemonstrator forEachDemonstrator;

	@BeforeAll
	public void setup() {
		forEachDemonstrator = new ForEachDemonstrator();

		System.setOut( new PrintStream( outputStreamCaptor ) );
	}

	@Test
	public void shouldOutputAllWordsPassedToTheConsole() {
		String words = forEachDemonstrator
				.runExampleFor( ImmutableList.of( "Some interesting sentence" ) );

		assertThat( outputStreamCaptor.toString().trim() ).isEqualTo( "Some interesting sentence" );
	}
}
