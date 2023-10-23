package com.fidelity.greeter;

import org.springframework.stereotype.Component;

// TODO: add a Spring annotation that identifies the AmarilloVisitor class as a Spring Bean
//        with the ID "amarilloVis"

@Component("amarilloVis")
public class AmarilloVisitor implements Visitor {
	private String name;
	private String greeting;

	public AmarilloVisitor(){
		System.out.println("created Amarillo visitor");
		this.name = "Joe Bob Springsteen";
		this.greeting = "Howdy";
	}
	@Override
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	@Override
	public String getGreeting() {
		return greeting;
	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AmarilloVisitor [name=" + name + ", greeting=" + greeting + "]";
	}
	
}
