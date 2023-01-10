package com.xdesign.hackronym.contents.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.xdesign.hackronym.task.TaskType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface CodeExample {
	String name();

	String description();

	String api();

	String chapter();

	TaskType taskType();

	String slashCommand();

	String slashParameters();

}
