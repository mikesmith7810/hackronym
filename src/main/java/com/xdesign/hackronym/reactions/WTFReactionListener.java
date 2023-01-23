package com.xdesign.hackronym.reactions;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.ReactionAddedEvent;
import com.xdesign.hackronym.retriever.AcronymRetriever;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class WTFReactionListener implements BoltEventHandler<ReactionAddedEvent>{

	private final AcronymRetriever acronymRetriever;

	public WTFReactionListener(final AcronymRetriever acronymRetriever ) {
		this.acronymRetriever = acronymRetriever;
	}

	@Override
	public Response apply(EventsApiPayload<ReactionAddedEvent> event, EventContext context) throws IOException, SlackApiException {
		log.info("To be implemented");

		final String reaction = event.getEvent().getReaction();

		log.info("Reaction is : " + reaction);
		return context.ack();
	}
}
