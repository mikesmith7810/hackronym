package com.xdesign.hackronym.helper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import static com.xdesign.hackronym.helper.MessageComposer.CODEBLOCK;
import static com.xdesign.hackronym.helper.MessageComposer.NEWLINE;
import static com.xdesign.hackronym.helper.MessageComposer.SPACE;
import static com.xdesign.hackronym.helper.MessageComposer.TAB;
import static com.xdesign.hackronym.helper.MessageComposer.bold;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Contents;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MessageComposerTest {

	@Mock
	private ContentsStore contentsStore;

	private TaskResult taskResult;

	private MessageComposer messageComposer;

	@BeforeEach
	public void setup() {
		this.messageComposer = new MessageComposer( contentsStore );
		this.taskResult = createTestTaskResult();

	}

	@Test
	public void shouldCreateMessageFromContents() {
		when( contentsStore.retrieveContents() ).thenReturn( createTestContents() );

		final String message = messageComposer.createMessageForContents();

		assertThat( message ).isEqualTo( bold( "Test Chapter 1" ) + NEWLINE + TAB + bold(
				"Red 1 Example" ) + NEWLINE + TAB + TAB + "This is a red test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + bold(
						"/test/api1" ) + NEWLINE + TAB + TAB + "slash command example : " + bold(
								"/slashtest1" ) + SPACE + "FUNCTON word1" + NEWLINE + NEWLINE + TAB + bold(
										"Green 2 Example" ) + NEWLINE + TAB + TAB + "This is a green test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + bold(
												"/test/api2" ) + NEWLINE + TAB + TAB + "slash command example : " + bold(
														"/slashtest2" ) + SPACE + "FUNCTON word1" + NEWLINE + NEWLINE +

				bold( "Test Chapter 2" ) + NEWLINE + TAB + bold(
						"Blue 1 Example" ) + NEWLINE + TAB + TAB + "This is a blue test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + bold(
								"/test/api3" ) + NEWLINE + TAB + TAB + "slash command example : " + bold(
										"/slashtest3" ) + SPACE + "FUNCTON word1" + NEWLINE + NEWLINE + TAB + bold(
												"Yellow 2 Example" ) + NEWLINE + TAB + TAB + "This is a yellow test piece of code" + NEWLINE + TAB + TAB + "rest endpoint : " + bold(
														"/test/api4" ) + NEWLINE + TAB + TAB + "slash command example : " + bold(
																"/slashtest4" ) + SPACE + "FUNCTON word1" + NEWLINE + NEWLINE );
	}

	@Test
	public void shouldCreateMessageForLearning() {
		final String message = messageComposer.createMessageForTaskResult( taskResult );

		assertThat( message ).isEqualTo( bold( "Result : " ) + NEWLINE + "Result" + NEWLINE + bold(
				"Description : " ) + NEWLINE + "Task description" + NEWLINE + bold(
				"Source Code : " ) + NEWLINE + CODEBLOCK + "Foreach source code" + CODEBLOCK );

	}

	private TaskResult createTestTaskResult() {
		return TaskResult.builder()
				.type( TaskType.FOREACH )
				.value( "Result" )
				.sourceCode( "Foreach source code" )
				.description( "Task description" )
				.build();
	}

	private Contents createTestContents() {
		return Contents.builder()
				.chapters( List.of(
						Chapter.builder()
								.name( "Test Chapter 1" )
								.examples( List.of(
										Example.builder()
												.name( "Red 1 Example" )
												.description( "This is a red test piece of code" )
												.slashCommand( "/slashtest1" )
												.slashParameters( "FUNCTON word1" )
												.apiCall( "/test/api1" )
												.sourceCode( "Foreach source code" )
												.taskType( TaskType.FOREACH )
												.build(),
										Example.builder()
												.name( "Green 2 Example" )
												.description( "This is a green test piece of code" )
												.slashCommand( "/slashtest2" )
												.slashParameters( "FUNCTON word1" )
												.apiCall( "/test/api2" )
												.sourceCode( "Predicate source code" )
												.taskType( TaskType.PREDICATE )
												.build() ) )
								.build(),
						Chapter.builder()
								.name( "Test Chapter 2" )
								.examples( List.of(
										Example.builder()
												.name( "Blue 1 Example" )
												.description( "This is a blue test piece of code" )
												.slashCommand( "/slashtest3" )
												.slashParameters( "FUNCTON word1" )
												.apiCall( "/test/api3" )
												.taskType( TaskType.SUPPLIER )
												.sourceCode( "Supplier source code" )
												.build(),
										Example.builder()
												.name( "Yellow 2 Example" )
												.slashCommand( "/slashtest4" )
												.slashParameters( "FUNCTON word1" )
												.apiCall( "/test/api4" )
												.description(
														"This is a yellow test piece of code" )
												.taskType( TaskType.CONSUMER )
												.sourceCode( "Consumer source code" )
												.build() ) )
								.build() ) )
				.build();
	}
}
