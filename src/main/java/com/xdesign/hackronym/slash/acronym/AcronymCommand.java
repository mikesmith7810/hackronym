package com.xdesign.hackronym.slash.acronym;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.retriever.AcronymRetriever;
import com.xdesign.hackronym.slash.MessageExtractingCommand;
import com.xdesign.hackronym.slash.annotations.SlashCommand;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("whatonearthis")
@Slf4j
@Component
public class AcronymCommand extends MessageExtractingCommand {

	private final AcronymRetriever acronymRetriever;

	public AcronymCommand(final AcronymRetriever acronymRetriever){
		this.acronymRetriever = acronymRetriever;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( "Deciphered : " + acronymRetriever.getAcronym(message).toString() )
				.build() );
	}
}
