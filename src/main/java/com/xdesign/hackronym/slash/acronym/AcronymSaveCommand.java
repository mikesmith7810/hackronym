package com.xdesign.hackronym.slash.acronym;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.slash_commands.response.SlashCommandResponse;
import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.xdesign.hackronym.domain.Acronym;
import com.xdesign.hackronym.slash.MessageExtractingCommand;
import com.xdesign.hackronym.slash.acronym.parser.AcronymParser;
import com.xdesign.hackronym.slash.acronym.validator.AcronymValidator;
import com.xdesign.hackronym.slash.annotations.SlashCommand;
import com.xdesign.hackronym.store.AcronymStorer;

import lombok.extern.slf4j.Slf4j;

@SlashCommand("addacronym")
@Slf4j
@Component
public class AcronymSaveCommand extends MessageExtractingCommand {
	final AcronymStorer acronymStorer;

	final AcronymValidator acronymValidator;

	final AcronymParser acronymParser;

	public AcronymSaveCommand( final AcronymStorer acronymStorer,
			final AcronymValidator acronymValidator, final AcronymParser acronymParser ) {
		this.acronymStorer = acronymStorer;
		this.acronymValidator = acronymValidator;
		this.acronymParser = acronymParser;
	}

	protected Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) {



		if ( acronymValidator.isValid( message ) ) {
			final Acronym acronym = acronymStorer.storeAcronym( acronymParser.parse( message ) );

			return context.ack( SlashCommandResponse.builder()
					.responseType( "in_channel" )
					.text( "Stored : " + acronym.toString() )
					.build() );
		} else {
			return context.ack( SlashCommandResponse.builder()
					.responseType( "in_channel" )
					.text( "Supplied acronym not valid. Example is : /addacronym ASAP,As soon as possible,Its pretty quick!" )
					.build() );
		}

	}

}
