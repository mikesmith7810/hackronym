package com.xdesign.hackronym.contents.bean;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.xdesign.hackronym.contents.ContentsStore;
import com.xdesign.hackronym.contents.annotation.CodeExample;
import com.xdesign.hackronym.demonstrators.functionalinterface.Demonstrator;
import com.xdesign.hackronym.domain.Example;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Data
@Slf4j
public class ContentsBeanPostProcessor implements BeanPostProcessor {

	public static final String JAVA = ".java";
	private ContentsStore contentsStore;

	@Value("${github.url}")
	private String gitHubUrl;

	public ContentsBeanPostProcessor( final ContentsStore contentsStore ) {
		this.contentsStore = contentsStore;
	}

	@Override
	public Object postProcessAfterInitialization( final Object bean, final String beanName )
			throws BeansException {
		final Class<?> beanClass = AopUtils.getTargetClass( bean );

		if ( beanClass.getAnnotations() == null || !( bean instanceof Demonstrator ) ) {
			return bean;
		}

		final List<Example> beanExamples = Arrays.stream( beanClass.getDeclaredMethods() )
				.filter( method -> method.isAnnotationPresent( CodeExample.class ) )
				.map( method -> method.getAnnotation( CodeExample.class ) )
				.map( annotation -> Example.builder()
						.name( annotation.name() )
						.description( annotation.description() )
						.githubLocation( generateGitHubURL( beanClass.getCanonicalName() ) )
						.apiCall( annotation.api() )
						.chapter( annotation.chapter() )
						.taskType( annotation.taskType() )
						.slashCommand( annotation.slashCommand() )
						.slashParameters( annotation.slashParameters() )
						.build() )
				.collect( Collectors.toList() );

		beanExamples.stream()
				.forEach( beanExample -> contentsStore.getExamples().add( beanExample ) );

		return bean;
	}

	public String generateGitHubURL( final String className ) {
		return gitHubUrl + className.replace( ".", "/" ) + JAVA;
	}
}
