package com.xdesign.hackronym.demonstrators.functionalinterface;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FunctionDemonstratorTest {

	public FunctionDemonstrator functionDemonstrator;

	@BeforeAll
	public void setup() {
		functionDemonstrator = new FunctionDemonstrator();
	}

	@Test
	public void shouldReverseAString() {
		final String result = functionDemonstrator.runExampleFor( ImmutableList.of( "example" ) );

		assertThat( result ).isEqualTo( "elpmaxe" );
	}
}
