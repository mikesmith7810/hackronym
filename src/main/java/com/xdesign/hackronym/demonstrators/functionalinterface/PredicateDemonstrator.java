package com.xdesign.hackronym.demonstrators.functionalinterface;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.annotation.CodeExample;
import com.xdesign.hackronym.task.TaskType;

@Component
public class PredicateDemonstrator extends Demonstrator {
	@CodeExample(name = "Predicate",
			description = "Predicate Code Example. Checks to see if a word starts with 's'.",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.PREDICATE,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "PREDICATE super")

	public boolean runExampleFor( final List<String> word ) {
		final Predicate<String> startsWithPredicate = w -> w.startsWith( "s" );

		return startsWithPredicate.test( word.get( 0 ) );
	}

	@CodeExample(name = "Predicate2",
			description = "Predicate2 Code Example. Filters supplied words starting with supplied letter.",
			api = "/java/functionalinterface",
			chapter = "Functional Interfaces",
			taskType = TaskType.PREDICATE2,
			slashCommand = "/learnjava/functionalinterfaces",
			slashParameters = "PREDICATE2 s stratocaster guitar gibson super jackson")

	public List<String> runExampleFor( final String letter, final List<String> words ) {
		return words.stream()
				.filter( word -> filterListByLetter( word, w -> w.startsWith( letter ) ) )
				.collect( Collectors.toList() );
	}

	private boolean filterListByLetter( final String word, final Predicate<String> predicate ) {
		return predicate.test( word );
	}
}
