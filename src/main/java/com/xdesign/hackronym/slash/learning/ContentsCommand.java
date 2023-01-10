package com.xdesign.hackronym.slash.learning;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.helper.MessageComposer;
import com.xdesign.hackronym.slash.MessageExtractingCommand;
import com.xdesign.hackronym.slash.annotations.SlashCommand;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("contents")
@Slf4j
@Component
public class ContentsCommand extends MessageExtractingCommand {

	private final MessageComposer messageComposer;

	public ContentsCommand( final MessageComposer messageComposer ) {
		this.messageComposer = messageComposer;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( messageComposer.createMessageForContents() )
				.build() );
	}
}
