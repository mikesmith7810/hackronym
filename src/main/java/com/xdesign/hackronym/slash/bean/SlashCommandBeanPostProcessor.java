package com.xdesign.hackronym.slash.bean;

import java.util.regex.Pattern;

import lombok.NonNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import com.slack.api.bolt.App;
import com.slack.api.bolt.handler.builtin.SlashCommandHandler;
import com.xdesign.hackronym.slash.annotations.SlashCommand;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * {@link BeanPostProcessor} which will identify
 * {@link org.springframework.context.annotation.Bean Beans} with a
 * {@link SlashCommand} annotation on them, and (if found) register the slash
 * command with this {@link App Bolt App}.
 */
@Component
@Data
@Slf4j
public final class SlashCommandBeanPostProcessor implements BeanPostProcessor {

	private final App app;

	@Override
	public Object postProcessAfterInitialization( @NonNull final Object bean,
			@NonNull final String beanName ) throws BeansException {

		final Class<?> beanClass = AopUtils.getTargetClass( bean );
		final SlashCommand ann = AnnotationUtils.findAnnotation( beanClass, SlashCommand.class );
		if ( ann == null || !( bean instanceof SlashCommandHandler ) ) {
			return bean;
		}

		final SlashCommandHandler schBean = (SlashCommandHandler) bean;

		log.info( "Processing slash command : " + beanName );
		if ( ann.isPattern() ) {
			this.app.command( Pattern.compile( "/" + ann.value() ), schBean );
			log.info( "registering slash command : " + Pattern.compile( "/" + ann.value() ) );
		} else {
			this.app.command( "/" + ann.value(), schBean );
			log.info( "registering slash command : " + "/" + ann
					.value() + " with bean : " + schBean.getClass().getName() );
		}

		return bean;
	}
}
