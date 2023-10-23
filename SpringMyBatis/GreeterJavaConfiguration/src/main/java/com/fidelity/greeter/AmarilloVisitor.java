package com.fidelity.greeter;

import org.springframework.stereotype.Component;

@Component
public class AmarilloVisitor implements Visitor {
	private String name;
	private String greeting;
	
	public AmarilloVisitor(){
		System.out.println("created Amarillo visitor");
		this.name = "Joe Bob";
		this.greeting = "Howdy";
	}
	
	public String getGreeting() {
		return greeting;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "AmarilloVisitor [name=" + name + ", greeting=" + greeting + "]";
	}
	
}
