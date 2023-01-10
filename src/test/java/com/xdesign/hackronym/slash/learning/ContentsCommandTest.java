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
import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Contents;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.helper.MessageComposer;
import com.xdesign.hackronym.task.TaskType;

@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContentsCommandTest {

	private ContentsCommand contentsCommand;

	@Mock
	private MessageComposer messageComposer;

	@Mock
	private ContentsStore contentsStore;

	@Mock
	private SlashCommandRequest slashCommandRequest;

	@Mock
	private SlashCommandContext slashCommandContext;

	private Contents contents;

	@BeforeEach
	public void setup() {
		contentsCommand = new ContentsCommand( messageComposer );

		this.contents = createTestContents();

		when( messageComposer.createMessageForContents() ).thenReturn( "Some contents" );
		when( slashCommandContext.ack( SlashCommandResponse.builder()
				.responseType( "in_channel" )
				.text( "Some contents" )
				.build() ) ).thenReturn( Response.builder().body( "blah" ).build() );
	}

	@Test
	public void shouldGetContentsMessage() {
		final Response response = contentsCommand.doRespond( "Test Message", slashCommandRequest,
				slashCommandContext );

		verify( messageComposer ).createMessageForContents();
	}

	@Test
	public void shouldReturnResponseWithContents() {

		final Response response = contentsCommand.doRespond( "Test Message", slashCommandRequest,
				slashCommandContext );

		assertThat( response.getBody() ).isEqualTo( "blah" );
	}

	private Contents createTestContents() {
		return Contents.builder()
				.chapters( List.of( Chapter.builder()
						.name( "Test Chapter" )
						.examples( List.of( Example.builder()
								.name( "Test Example" )
								.sourceCode( "public void shouldTest(){//somecode};" )
								.taskType( TaskType.FOREACH )
								.build() ) )
						.build() ) )
				.build();
	}
}
