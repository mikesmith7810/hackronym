package com.xdesign.hackronym.teachers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.google.common.collect.ImmutableList;
import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.demonstrators.optional.CreationDemonstrator;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Contents;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class OptionalTeacherTest {

	OptionalTeacher optionalTeacher;

	@Mock
	CreationDemonstrator creationDemonstrator;

	@Mock
	ContentsStore contentsStore;

	@BeforeEach
	public void setup() {
		this.optionalTeacher = new OptionalTeacher( creationDemonstrator, contentsStore );
	}

	@Test
	public void shouldCallDemonstrator() {
		when( creationDemonstrator.runExampleFor( ImmutableList.of( "iamatest" ) ) )
				.thenReturn( Optional.of( "iamatest" ) );

		final String result = optionalTeacher.runTaskBasedUponType( TaskType.CREATION,
				ImmutableList.of( "iamatest" ) );

		verify( creationDemonstrator ).runExampleFor( ImmutableList.of( "iamatest" ) );
	}

	@Test
	public void shouldHaveAResultWithAllFields() {
		when( creationDemonstrator.runExampleFor( ImmutableList.of( "word1" ) ) )
				.thenReturn( Optional.of( "word1" ) );
		when( contentsStore.retrieveContents() ).thenReturn( Contents.builder()
				.chapters( List.of( Chapter.builder()
						.examples( List.of( Example.builder()
								.taskType( TaskType.CREATION )
								.sourceCode( "some code" )
								.description( "creation example" )
								.build() ) )
						.build() ) )
				.build() );

		final TaskResult result = optionalTeacher.teachThis( Task.builder()
				.taskType( TaskType.CREATION )
				.parameters( List.of( "word1" ) )
				.build() );

		assertThat( result.getValue() ).isEqualTo( "word1" );
		assertThat( result.getType() ).isEqualTo( TaskType.CREATION );
		assertThat( result.getDescription() ).isEqualTo( "creation example" );
		assertThat( result.getSourceCode() ).isEqualTo( "some code" );
	}
}
