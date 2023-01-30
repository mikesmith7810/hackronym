package com.xdesign.hackronym.event.bean;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.slack.api.bolt.App;
import com.slack.api.bolt.handler.BoltEventHandler;
import com.slack.api.model.event.Event;

import lombok.extern.slf4j.Slf4j;

/**
 * {@link BeanPostProcessor} which will automatically register any
 * {@link BoltEventHandler} {@link org.springframework.context.annotation.Bean
 * Beans} with this {@link App Bolt App}.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EventListenerBeanPostProcessor implements BeanPostProcessor {

	private final @NonNull App app;

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object postProcessAfterInitialization( @NonNull final Object bean,
			@NonNull final String beanName ) throws BeansException {

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
		return Arrays.stream(beanClass.getGenericInterfaces())
				.filter(ParameterizedType.class::isInstance)
				.map(ParameterizedType.class::cast)
				.filter(t -> BoltEventHandler.class.equals(t.getRawType()))
				.map(ParameterizedType::getActualTypeArguments)
				.map(t -> t[0])
				.findFirst()
				.map(Class.class::cast)
				.orElse(null);
	}
}
