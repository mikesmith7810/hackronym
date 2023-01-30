package com.xdesign.hackronym.slash;

import java.io.IOException;
import java.util.Optional;

import com.slack.api.bolt.context.builtin.SlashCommandContext;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.slack.api.bolt.request.builtin.SlashCommandRequest;
import com.slack.api.bolt.response.Response;
import com.slack.api.methods.SlackApiException;

import lombok.extern.slf4j.Slf4j;

/**
 * Basic {@link SlashCommandHandler} which will extract the message (everything
 * after the name of the command) when invoked, and provide it to the
 * {@link #doRespond(String, SlashCommandRequest, SlashCommandContext)}
 * implementation.
 */
@Slf4j
public abstract class MessageExtractingCommand implements SlashCommandHandler {

	@Override
	public final Response apply( final SlashCommandRequest request,
			final SlashCommandContext context ) throws IOException, SlackApiException {

		final var message = Optional.ofNullable(request.getPayload().getText())
				.map(String::trim)
				.orElse("");

		try {
			return doRespond( message, request, context );
		} catch ( final IOException | SlackApiException ex ) {
			throw ex;
		} catch ( final Exception ex ) {
			return context.ack( "Operation failed: " + ex.getMessage() );
		}
	}

	/**
	 * Responds to the given {@link SlashCommandRequest}, submitted under the given
	 * {@link SlashCommandContext}.
	 *
	 * @param message
	 *            the message (remainder of invocation message after the name of the
	 *            command) to respond to.
	 * @param request
	 *            the {@code Request} to respond to
	 * @param context
	 *            the {@code Context} under which the {@code request} was submitte
	 *
	 * @return the {@link Response} to the given {@code request}
	 */
	protected abstract Response doRespond( final String message, final SlashCommandRequest request,
			final SlashCommandContext context ) throws IOException, SlackApiException;
}
