package com.xdesign.hackronym.contents.bean;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.xdesign.hackronym.contents.ContentsStore;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class ContentsBeanPostProcessorTest {
	private ContentsBeanPostProcessor contentsBeanPostProcessor;

	@Mock
	private ContentsStore contentsStore;

	@BeforeEach
	public void setup() {
		contentsBeanPostProcessor = new ContentsBeanPostProcessor( contentsStore );
		contentsBeanPostProcessor.setGitHubUrl( "http://www.test.com/reponame/app/src/main/java/" );
	}

	@Test
	public void shouldGenerateGitHubURLFromClassName() {
		final String className = "com.xdesign.cake.demonstrators.optional.CreationDemonstrator";

		final String gitHubUrl = contentsBeanPostProcessor.generateGitHubURL( className );

		assertThat( gitHubUrl ).isEqualTo(
				"http://www.test.com/reponame/app/src/main/java/com/xdesign/cake/demonstrators/optional/CreationDemonstrator.java" );
	}

}
