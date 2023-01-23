package com.xdesign.hackronym.reactions;

import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.slack.api.Slack;
import com.slack.api.app_backend.events.payload.EventsApiPayload;
import com.slack.api.bolt.context.builtin.EventContext;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.model.event.ReactionAddedEvent;
import com.xdesign.hackronym.retriever.AcronymRetriever;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WTFReactionListenerTest {

	private WTFReactionListener wtfReactionListener;

	@Mock
	private AcronymRetriever acronymRetriever;

	@Mock
	private AcronymScanner acronymScanner;

	@Mock
	private EventsApiPayload<ReactionAddedEvent> eventsApiPayload;

	@Mock
	private EventContext eventContext;

	@Mock
	private Slack slackInstance;

	@Mock
	private MethodsClient methodsClient;

	@BeforeEach
	public void setup() {
		this.wtfReactionListener = new WTFReactionListener( acronymRetriever, acronymScanner );
	}


	void shouldScanMessage() throws SlackApiException, IOException {

		try (MockedStatic<Slack> slack = Mockito.mockStatic(Slack.class)) {
        slack.when(Slack::getInstance).thenReturn(slackInstance);

		when(slackInstance.methods()).thenReturn(methodsClient);

        wtfReactionListener.apply(eventsApiPayload,eventContext);

		//verify( acronymScanner ).scanForAcronyms();

    }



	}

}
