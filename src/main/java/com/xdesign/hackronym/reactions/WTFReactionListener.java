package com.xdesign.hackronym.reactions;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.slack.api.Slack;
import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.response.chat.ChatPostMessageResponse;
import com.slack.api.model.event.ReactionAddedEvent;
import com.xdesign.hackronym.retriever.AcronymRetriever;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WTFReactionListener implements BoltEventHandler<ReactionAddedEvent>{

	private final AcronymRetriever acronymRetriever;

	private final AcronymScanner acronymScanner;

	public WTFReactionListener( final AcronymRetriever acronymRetriever,
			final AcronymScanner acronymScanner ) {
		this.acronymRetriever = acronymRetriever;
		this.acronymScanner = acronymScanner;
	}

	@Override
	public Response apply( final EventsApiPayload<ReactionAddedEvent> event,
			final EventContext context ) throws IOException, SlackApiException {
		final String reaction = event.getEvent().getReaction();

		final var client = Slack.getInstance().methods();

		log.info( "reaction : " + reaction );
		if ( reaction.equals( "wtf" ) ) {

			final List<String> acronymsFound = acronymScanner
					.scanForAcronyms( retrieveMessage( event, client ) );

			final List<String> acronymResults = acronymsFound.stream()
					.map( acronymRetriever::getAcronym )
					.collect( Collectors.toList() );

			final ChatPostMessageResponse response = context.client()
					.chatPostMessage( m -> m.channel( event.getEvent().getItem().getChannel() )
							.token( System.getenv( "SLACK_BOT_TOKEN" ) )
							.text( "*" + "Acronyms Analysed!" + "*" + "\n" + String.join( "\n",
									acronymResults ) ) );

		}
		return context.ack();
	}

	private String retrieveMessage( final EventsApiPayload<ReactionAddedEvent> event,
			final MethodsClient client ) throws IOException, SlackApiException {

		final var result = client.conversationsHistory( r -> r

				.token( System.getenv( "SLACK_BOT_TOKEN" ) )
				.channel( event.getEvent().getItem().getChannel() )
				.latest( event.getEvent().getItem().getTs() )
				.inclusive( true )
				.limit( 1 ) );

		return result.getMessages().get( 0 ).getText();
	}
}
