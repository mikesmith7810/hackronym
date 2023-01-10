package com.xdesign.hackronym.slash.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Composite annotation for marking a
 * {@link org.springframework.context.annotation.Bean} as a slash command.
 * Should only be used on
 * {@link com.slack.api.bolt.handler.builtin.SlashCommandHandler}
 * implementations.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface SlashCommand {

	/**
	 * The name of the slash command which the annotated class should respond to.
	 *
	 * @return the name of the slash command
	 */
	String value();

	/**
	 * Whether the {@link #value()} denotes a regex-pattern for matching against.
	 * Default is {@code false}.
	 *
	 * @return {@code true} if the {@code value} of this annotation denotes a
	 *         regex-pattern for matching slash command names against; otherwise
	 *         {@code false}
	 */
	boolean isPattern() default false;
}
