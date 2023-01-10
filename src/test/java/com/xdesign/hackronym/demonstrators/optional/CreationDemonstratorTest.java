package com.xdesign.hackronym.demonstrators.optional;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class CreationDemonstratorTest {

	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

	private CreationDemonstrator creationDemonstrator;

	@BeforeAll
	public void setup() {
		creationDemonstrator = new CreationDemonstrator();

		System.setOut( new PrintStream( outputStreamCaptor ) );
	}

	@Test
	public void shouldCreateAnOptional() {
		Optional<String> optional = creationDemonstrator
				.runExampleFor( ImmutableList.of( "Some interesting sentence" ) );

		assertThat( optional.isPresent() ).isTrue();
	}
}
