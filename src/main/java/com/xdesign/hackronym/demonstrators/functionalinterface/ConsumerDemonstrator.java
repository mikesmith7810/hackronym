package com.xdesign.hackronym.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Consumer;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.annotation.CodeExample;
import com.xdesign.hackronym.task.TaskType;

@Component
public class ConsumerDemonstrator extends Demonstrator {
	@CodeExample(name = "Consumer",
			description = "Consumer Code Example. Sends a message to the console",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.CONSUMER,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "CONSUMER word1")

	public String runExampleFor( final List<String> input ) {
		final Consumer<String> consumer = string -> System.out.println( string );

		consumer.accept( input.get( 0 ) );
		return "SENT TO CONSOLE";
	}
}
