package com.xdesign.hackronym.event.bean;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import org.jetbrains.annotations.NotNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.slack.api.bolt.App;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.model.event.Event;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link BeanPostProcessor} which will automatically register any
 * {@link BoltEventHandler} {@link org.springframework.context.annotation.Bean
 * Beans} with this {@link App Bolt App}.
 */
@Component
@Slf4j
public class EventListenerBeanPostProcessor implements BeanPostProcessor {

	private final App app;

	public EventListenerBeanPostProcessor( final App app ) {
		Assert.notNull( app, "'app' must not be 'null'" );

		this.app = app;
	}

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object postProcessAfterInitialization( @NotNull final Object bean,
			@NotNull final String beanName ) throws BeansException {

		final Class<?> beanClass = AopUtils.getTargetClass( bean );
		if ( !BoltEventHandler.class.isAssignableFrom( beanClass ) ) {
			return bean;
		}

		final Class<? extends Event> eventType = getEventType( beanClass );
		if ( eventType != null ) {
			log.info( "Registering event : " + eventType.getName() );
			this.app.event( eventType, (BoltEventHandler) bean );
		}
		return bean;
	}

	@SuppressWarnings("unchecked")
	private Class<? extends Event> getEventType( final Class<?> beanClass ) {
		// Use reflection to determine the generic type of event which the BoltEventHandler instance
		// is for.
		final Type[] genericInterfaces = beanClass.getGenericInterfaces();
		for ( final Type type : genericInterfaces ) {
			if ( type instanceof ParameterizedType ) {
				final ParameterizedType pType = (ParameterizedType) type;
				final Type raw = pType.getRawType();
				if ( !BoltEventHandler.class.equals( raw ) ) {
					continue;
				}
				final Type[] typeArgs = pType.getActualTypeArguments();
				return (Class<? extends Event>) typeArgs[0];
			}
		}
		return null;
	}
}
