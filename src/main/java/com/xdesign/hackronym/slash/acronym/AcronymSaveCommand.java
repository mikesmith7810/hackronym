package com.xdesign.hackronym.slash.acronym;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.slash.MessageExtractingCommand;
import com.xdesign.hackronym.slash.annotations.SlashCommand;
import com.xdesign.hackronym.store.AcronymStorer;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("addacronym")
@Slf4j
@Component
@RequiredArgsConstructor
public class AcronymSaveCommand extends MessageExtractingCommand {
	private final @NonNull AcronymStorer acronymStorer;

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {

		return context.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( acronymStorer.storeAcronym( message ) )
				.build() );

	}

}
