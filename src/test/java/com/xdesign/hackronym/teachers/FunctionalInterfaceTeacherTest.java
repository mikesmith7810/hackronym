package com.xdesign.hackronym.teachers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.demonstrators.functionalinterface.ConsumerDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.FunctionDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.PredicateDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.SupplierDemonstrator;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Contents;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class FunctionalInterfaceTeacherTest {

	FunctionalInterfaceTeacher functionalInterfacesTeacher;

	@Mock
	FunctionDemonstrator functionDemonstrator;

	@Mock
	ConsumerDemonstrator consumerDemonstrator;

	@Mock
	PredicateDemonstrator predicateDemonstrator;

	@Mock
	SupplierDemonstrator supplierDemonstrator;

	@Mock
	ContentsStore contentsStore;

	@BeforeEach
	void setup() {
		this.functionalInterfacesTeacher = new FunctionalInterfaceTeacher( functionDemonstrator,
				consumerDemonstrator,
				predicateDemonstrator,
				supplierDemonstrator,
				contentsStore );
	}

	@Test
	void shouldUseFunctionToReverseString() {
		final String result = functionalInterfacesTeacher.runTaskBasedUponType( TaskType.FUNCTION,
				ImmutableList.of( "iamatest" ) );

		verify( functionDemonstrator ).runExampleFor( ImmutableList.of( "iamatest" ) );
	}

	@Test
	void shouldUseConsumerToPrintToConsole() {
		functionalInterfacesTeacher.runTaskBasedUponType( TaskType.CONSUMER,
				ImmutableList.of( "Hello World" ) );

		verify( consumerDemonstrator ).runExampleFor( ImmutableList.of( "Hello World" ) );
	}

	@Test
	void shouldUsePredicateToCheckStringStartsWithLetter() {
		final String result = functionalInterfacesTeacher.runTaskBasedUponType( TaskType.PREDICATE,
				ImmutableList.of( "Super" ) );

		verify( predicateDemonstrator ).runExampleFor( ImmutableList.of( "Super" ) );
	}

	@Test
	void shouldUseSupplierToGetTheCurrentDate() {
		final String result = functionalInterfacesTeacher.runTaskBasedUponType( TaskType.SUPPLIER,
				ImmutableList.of( "Super" ) );

		verify( supplierDemonstrator ).runExampleFor();
	}

	@Test
	public void shouldHaveAResultWithAllFields() {
		when( functionalInterfacesTeacher.runTaskBasedUponType( TaskType.FUNCTION,
				ImmutableList.of( "porsche" ) ) ).thenReturn( "ehcsrop" );
		when( contentsStore.retrieveContents() ).thenReturn( Contents.builder()
				.chapters( List.of( Chapter.builder()
						.examples( List.of( Example.builder()
								.taskType( TaskType.FUNCTION )
								.sourceCode( "some code" )
								.description( "function example" )
								.build() ) )
						.build() ) )
				.build() );

		final TaskResult result = functionalInterfacesTeacher.teachThis( Task.builder()
				.taskType( TaskType.FUNCTION )
				.parameters( List.of( "porsche" ) )
				.build() );

		assertThat( result.getValue() ).isEqualTo( "ehcsrop" );
		assertThat( result.getType() ).isEqualTo( TaskType.FUNCTION );
		assertThat( result.getDescription() ).isEqualTo( "function example" );
		assertThat( result.getSourceCode() ).isEqualTo( "some code" );
	}
}
