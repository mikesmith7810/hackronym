package com.xdesign.hackronym.teachers;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.demonstrators.functionalinterface.ConsumerDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.FunctionDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.PredicateDemonstrator;
import com.xdesign.hackronym.demonstrators.functionalinterface.SupplierDemonstrator;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.task.Task;
import com.xdesign.hackronym.task.TaskResult;
import com.xdesign.hackronym.task.TaskType;

@Component
public class FunctionalInterfaceTeacher {

	private final FunctionDemonstrator functionDemonstrator;

	private final ConsumerDemonstrator consumerDemonstrator;

	private final PredicateDemonstrator predicateDemonstrator;

	private final SupplierDemonstrator supplierDemonstrator;

	private final ContentsStore contentsStore;

	public static final String TYPE_NOT_RECOGNISED = "Type not recognised";

	public FunctionalInterfaceTeacher( final FunctionDemonstrator functionDemonstrator,
			final ConsumerDemonstrator consumerDemonstrator,
			final PredicateDemonstrator predicateDemonstrator,
			final SupplierDemonstrator supplierDemonstrator, final ContentsStore contentsStore ) {
		this.functionDemonstrator = functionDemonstrator;
		this.consumerDemonstrator = consumerDemonstrator;
		this.predicateDemonstrator = predicateDemonstrator;
		this.supplierDemonstrator = supplierDemonstrator;
		this.contentsStore = contentsStore;
	}

	public TaskResult teachThis( final Task task ) {

		final Example example = getExampleBasedOn( task );

		return TaskResult.builder()
				.type( task.getTaskType() )
				.value( runTaskBasedUponType( task.getTaskType(), task.getParameters() ) )
				.sourceCode(
						example
						.getSourceCode() )
				.description( example
						.getDescription() )
				.build();
	}

	public String runTaskBasedUponType( final TaskType type, final List<String> input ) {

		switch ( type ) {
		case FUNCTION:
			return functionDemonstrator.runExampleFor( input );
		case CONSUMER:
			return consumerDemonstrator.runExampleFor( input );
		case PREDICATE:
			return String.valueOf( predicateDemonstrator.runExampleFor( input ) );
		case PREDICATE2:
			return String.valueOf( predicateDemonstrator.runExampleFor( input.get( 0 ),
					input.subList( 1, input.size() ) ) );
		case SUPPLIER:
			return supplierDemonstrator.runExampleFor();
		default:
			return TYPE_NOT_RECOGNISED;
		}
	}

	@NotNull
	private Example getExampleBasedOn( final Task task ) {
		return contentsStore.retrieveContents()
				.getChapters()
				.stream()
				.map( chapter -> chapter.getExamples() )
				.flatMap( examples -> examples.stream()
						.filter( example -> example.getTaskType().equals( task.getTaskType() ) ) )
				.findFirst()
				.get();
	}
}
