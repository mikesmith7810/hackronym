package com.xdesign.hackronym.helper;

import java.util.function.Function;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.task.TaskResult;

@Component
public class MessageComposer {
	public static final String NEWLINE = "\n";
	public static final String TAB = "\t";
	public static final String BOLD = "*";
	public static final String CODEBLOCK = "```";
	public static final String SPACE = " ";

	private ContentsStore contentStore;

	public MessageComposer( final ContentsStore contentsStore ) {
		this.contentStore = contentsStore;
	}

	public String createMessageForContents() {

		return contentStore.retrieveContents()
				.getChapters()
				.stream()
				.map( createChapterMessage() )
				.collect( StringBuilder::new, StringBuilder::append, StringBuilder::append )
				.toString();
	}

	public String createMessageForTaskResult( final TaskResult taskResult ) {

		return bold( "Result : " ) + NEWLINE + taskResult.getValue() + NEWLINE + bold(
				"Description : " ) + NEWLINE + taskResult.getDescription() + NEWLINE + bold(
						"Source Code : " ) + NEWLINE + CODEBLOCK + taskResult
						.getSourceCode() + CODEBLOCK;
	}

	@NotNull
	private static Function<Chapter, String> createChapterMessage() {
		return chapter -> bold( chapter.getName() ) + NEWLINE + chapter.getExamples()
				.stream()
				.map( example -> TAB + bold( example.getName() ) + NEWLINE + TAB + TAB + example
						.getDescription() + NEWLINE + TAB + TAB + "rest endpoint : " + bold( example
								.getApiCall() ) + NEWLINE + TAB + TAB + "slash command example : " + bold(
										example.getSlashCommand() ) + SPACE + example
												.getSlashParameters() + NEWLINE + NEWLINE )
				.collect( StringBuilder::new, StringBuilder::append, StringBuilder::append );
	}

	public static String bold( final String test ) {
		return BOLD + test + BOLD;
	}

}
