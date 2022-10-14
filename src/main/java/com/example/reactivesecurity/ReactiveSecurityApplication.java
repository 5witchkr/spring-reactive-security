package com.example.reactivesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ReactorHttpHandlerAdapter;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.adapter.WebHttpHandlerBuilder;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

@ComponentScan(basePackages = {"com.example.reactivesecurity"})
@EnableWebFlux
public class ReactiveSecurityApplication {

	public static void main(String[] args) {
		try (AnnotationConfigApplicationContext context
					 = new AnnotationConfigApplicationContext(
				ReactiveSecurityApplication.class)) {

			context.getBean(DisposableServer.class).onDispose().block();
		}
	}
	@Bean
	public DisposableServer disposableServer(ApplicationContext context) {
		HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context)
				.build();
		ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
		HttpServer httpServer = HttpServer.create().host("localhost").port(8087);
		return httpServer.handle(adapter).bindNow();
	}
}
