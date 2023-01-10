package com.xdesign.hackronym.demonstrators.streams;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.annotation.CodeExample;
import com.xdesign.hackronym.demonstrators.functionalinterface.Demonstrator;
import com.xdesign.hackronym.task.TaskType;

@Component
public class ForEachDemonstrator extends Demonstrator {
	@CodeExample(name = "Streams ForEach",
			description = "Outputs list of strings to console",
			api = "/java/streams",
			chapter = "Streams",
			taskType = TaskType.FOREACH,
			slashCommand = "/learnjava/streams",
			slashParameters = "FOREACH word1 word2")

	public String runExampleFor( final List<String> input ) {

		input.stream().forEach( word -> System.out.println( word ) );

		return "SENT TO CONSOLE";
	}
}
