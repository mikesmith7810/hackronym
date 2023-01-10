package com.xdesign.hackronym.slash.learning;

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

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.controller.OptionalController;
import com.xdesign.hackronym.helper.MessageComposer;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LearnOptionalsCommandTest {

	private LearnOptionalCommand learnOptionalCommand;

	@Mock
	private OptionalController optionalController;

	@Mock
	private MessageComposer messageComposer;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	private Task task;

	private TaskResult taskResult;

	@BeforeEach
	public void setup() {

		this.learnOptionalCommand = new LearnOptionalCommand(optionalController, messageComposer );

		this.task = Task.builder()
				.taskType( TaskType.valueOf( "CREATION" ) )
				.parameters( List.of( "word1" ) )
				.build();

		this.taskResult = TaskResult.builder()
				.type( TaskType.CREATION )
				.value( "word1" )
				.sourceCode( "Some source code" )
				.description( ( "task description" ) )
				.build();
	}

	@Test
	public void shouldCallController() {

		final Response response = learnOptionalCommand.doRespond( "CREATION word1",
				slashCommandRequest, slashCommandContext );

		verify(optionalController).runLearningMaterial(task);
	}

	@Test
	public void shouldHaveAResponseWithAMessageInIt() {

		when( optionalController.runLearningMaterial(task) )
				.thenReturn( taskResult );

		when( slashCommandContext.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForTaskResult( taskResult ) )
				.build() ) ).thenReturn( Response.builder().body( "blah" ).build() );

		final Response response = learnOptionalCommand.doRespond( "CREATION word1",
				slashCommandRequest, slashCommandContext );

		assertThat( response.getBody() ).isEqualTo( "blah" );
	}
}
