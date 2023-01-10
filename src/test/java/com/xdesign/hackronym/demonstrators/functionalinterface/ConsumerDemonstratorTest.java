package com.xdesign.hackronym.demonstrators.functionalinterface;

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
public class ConsumerDemonstratorTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	private ConsumerDemonstrator consumerDemonstrator;

	@BeforeAll
	public void setup() {
		consumerDemonstrator = new ConsumerDemonstrator();

		System.setOut( new PrintStream( outputStreamCaptor ) );
	}

	@Test
	public void shouldPrintSomeOutputToConsole() {
		consumerDemonstrator.runExampleFor( ImmutableList.of( "Some interesting sentence" ) );

		assertThat( outputStreamCaptor.toString().trim() ).isEqualTo( "Some interesting sentence" );
	}
}
