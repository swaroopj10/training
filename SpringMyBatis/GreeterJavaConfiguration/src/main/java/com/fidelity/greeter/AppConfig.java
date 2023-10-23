package com.fidelity.greeter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean(name="amarilloVis")
	public Visitor createAmarilloVisitor(){
		return new AmarilloVisitor();
	}
	
	@Bean(name="indiaVis")
	public Visitor createIndiaVisitor(){
		return new IndiaVisitor();
	}
	
	@Bean(name="greeter")
	public Greeter createGreeter(){
		return new PopupGreeter();
	}
}
