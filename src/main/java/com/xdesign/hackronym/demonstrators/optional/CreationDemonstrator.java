package com.xdesign.hackronym.demonstrators.optional;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.annotation.CodeExample;
import com.xdesign.hackronym.demonstrators.functionalinterface.Demonstrator;
import com.xdesign.hackronym.task.TaskType;

@Component
public class CreationDemonstrator extends Demonstrator {
	@CodeExample(name = "Optional Creation",
			description = "Creates an Optional String",
			api = "/java/optional",
			chapter = "Optionals",
			taskType = TaskType.CREATION,
			slashCommand = "/learnjava/optionals",
			slashParameters = "CREATION word1")

	public Optional<String> runExampleFor( final List<String> input ) {
		final Optional<String> optional = Optional.of( input.get( 0 ) );

		return optional;
	}
}
