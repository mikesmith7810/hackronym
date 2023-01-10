package com.xdesign.hackronym;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.xdesign.hackronym.domain.Chapter;
import com.xdesign.hackronym.domain.Example;
import com.xdesign.hackronym.task.TaskType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ScratchPad {

	public static Set<Chapter> chapters = new HashSet<>();
	public static List<Example> allExamples = new ArrayList<>();
	public static List<Example> functionExamples = new ArrayList<>();
	public static List<Example> optionalExamples = new ArrayList<>();
	public static List<Example> streamsExamples = new ArrayList<>();

	public static void main( String[] args ) {

		getSourceCodeFromExamples();

		//chapterExampleCreation();
	}

	private static void getSourceCodeFromExamples() {
		createTestDataSourceCode();
		TaskType taskType = TaskType.FOREACH;

		String sourceCode = chapters.stream()
				.map( chapter -> chapter.getExamples() )
				.flatMap( examples -> examples.stream()
						.filter( example -> example.getTaskType().equals( taskType ) ) )
				.findFirst()
				.get()
				.getSourceCode();

		log.info( sourceCode );
	}

	private static void chapterExampleCreation() {
		createTestDataChapter();

		Set<Chapter> uniqueChapters = new HashSet<>();
		allExamples.stream()
				.forEach( example -> uniqueChapters.add( Chapter.builder()
						.name( example.getChapter() )
						.examples( new ArrayList<>() )
						.build() ) );

		log.info( "created unique chapters" );

		uniqueChapters.stream().forEach( chapter -> {
			allExamples.stream()
					.filter( example -> chapter.getName().equals( example.getChapter() ) )
					.forEach( example -> chapter.getExamples().add( example ) );
		} );

		log.info( "added examples" );
	}

	private static void createTestDataSourceCode() {

		functionExamples = List.of(
				Example.builder()
						.chapter( "Functions" )
						.description( "function 1 example" )
						.taskType( TaskType.FUNCTION )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 2 example" )
						.taskType( TaskType.CONSUMER )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 2 example" )
						.taskType( TaskType.SUPPLIER )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function example" )
						.taskType( TaskType.PREDICATE )
						.sourceCode( "Some Code" )
						.build() );
		streamsExamples = List.of(

				Example.builder()
						.chapter( "Streams" )
						.description( "lambda 1 example" )
						.taskType( TaskType.FOREACH )
						.sourceCode( "Some Code Foreac" )
						.build(),
				Example.builder()
						.chapter( "Streams" )
						.description( "lambdas 2 example" )
						.taskType( TaskType.SUM )
						.sourceCode( "Some Code" )
						.build() );
		optionalExamples = List.of(
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 1 example" )
						.taskType( TaskType.CREATION )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 2 example" )
						.taskType( TaskType.CHECK_VALUE )
						.sourceCode( "Some Code" )
						.build() );

		chapters.add( Chapter.builder().name( "Functions" ).examples( functionExamples ).build() );
		chapters.add( Chapter.builder().name( "Optionals" ).examples( optionalExamples ).build() );
		chapters.add( Chapter.builder().name( "Lambdas" ).examples( streamsExamples ).build() );

	}

	private static void createTestDataChapter() {
		chapters.add( Chapter.builder().name( "Functions" ).build() );
		chapters.add( Chapter.builder().name( "Optionals" ).build() );
		chapters.add( Chapter.builder().name( "Lambdas" ).build() );

		allExamples = List.of(
				Example.builder()
						.chapter( "Functions" )
						.description( "function 1 example" )
						.taskType( TaskType.FUNCTION )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder().chapter( "Lambdas" ).description( "lambda 1 example" ).build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 2 example" )
						.taskType( TaskType.CONSUMER )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 1 example" )
						.taskType( TaskType.CREATION )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Optionals" )
						.description( "optioanls 2 example" )
						.taskType( TaskType.CHECK_VALUE )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Lambdas" )
						.description( "lambdas 2 example" )
						.taskType( TaskType.FOREACH )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function 3 example" )
						.taskType( TaskType.CONSUMER )
						.sourceCode( "Some Code" )
						.build(),
				Example.builder()
						.chapter( "Functions" )
						.description( "function example" )
						.taskType( TaskType.PREDICATE )
						.sourceCode( "Some Code" )
						.build() );
	}
}
