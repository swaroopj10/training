package com.fidelity.greeter;

import org.springframework.stereotype.Component;

@Component("bostonVis")
public class BostonVisitor implements Visitor {
	
	private String name;
	private String greeting;
	
	public BostonVisitor(){
		System.out.println("created Boston visitor");
		this.name = "Brad";
		this.greeting = "Best Instructor";
	} 
	@Override
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "BostonVisitor [name=" + name + ", greeting=" + greeting + "]";
	}
	@Override
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getGreeting() {
		return greeting;
	}

}
