package com.xdesign.hackronym.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.slack.api.bolt.App;

@Configuration
public class HackronymConfig {

	@Bean
	public App hackronymApp() {
		App app = new App();
		//		app.command( "/hello", ( req, ctx ) -> {
		//			return ctx.ack( "What's up?" );
		//		} );
		//

		return app;
	}
}
