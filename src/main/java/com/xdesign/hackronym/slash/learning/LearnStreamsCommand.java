package com.xdesign.hackronym.slash.learning;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.controller.StreamsController;
import com.xdesign.hackronym.helper.MessageComposer;
import com.xdesign.hackronym.slash.MessageExtractingCommand;
import com.xdesign.hackronym.slash.annotations.SlashCommand;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("learnjava/streams")
@Slf4j
@Component
public class LearnStreamsCommand extends MessageExtractingCommand {

	private final StreamsController streamsController;

	private final MessageComposer messageComposer;

	public LearnStreamsCommand( final StreamsController streamsController,
			final MessageComposer messageComposer ) {
		this.streamsController = streamsController;
		this.messageComposer = messageComposer;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		final String[] arguments = message.split( " " );

		final TaskResult result = streamsController.runLearningMaterial( Task.builder()
				.taskType( TaskType.valueOf( arguments[0] ) )
				.parameters( Arrays.asList( arguments ).subList( 1, arguments.length ) )
				.build() );

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForTaskResult( result ) )
				.build() );
	}
}
