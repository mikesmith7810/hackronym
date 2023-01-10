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
import com.xdesign.hackronym.controller.FunctionalInterfacesController;
import com.xdesign.hackronym.helper.MessageComposer;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LearnFunctionalInterfacesCommandTest {

	private LearnFunctionalInterfacesCommand learnFunctionalInterfacesCommand;

	@Mock
	private FunctionalInterfacesController functionalInterfacesController;

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

		this.learnFunctionalInterfacesCommand = new LearnFunctionalInterfacesCommand(functionalInterfacesController, messageComposer );

		this.task = Task.builder()
				.taskType( TaskType.valueOf( "FUNCTION" ) )
				.parameters( List.of( "porsche" ) )
				.build();

		this.taskResult = TaskResult.builder()
				.type( TaskType.FUNCTION )
				.value( "ehcsrop" )
				.sourceCode( "Some source code" )
				.description( ( "task description" ) )
				.build();
	}

	@Test
	public void shouldCallController() {

		final Response response = learnFunctionalInterfacesCommand.doRespond( "FUNCTION porsche",
				slashCommandRequest, slashCommandContext );

		verify(functionalInterfacesController).runLearningMaterial( task );
	}

	@Test
	public void shouldHaveAResponseWithAMessageInIt() {

		when( functionalInterfacesController.runLearningMaterial( task ) )
				.thenReturn( taskResult );

		when( slashCommandContext.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForTaskResult( taskResult ) )
				.build() ) ).thenReturn( Response.builder().body( "blah" ).build() );

		final Response response = learnFunctionalInterfacesCommand.doRespond( "FUNCTION porsche",
				slashCommandRequest, slashCommandContext );

		assertThat( response.getBody() ).isEqualTo( "blah" );
	}
}
