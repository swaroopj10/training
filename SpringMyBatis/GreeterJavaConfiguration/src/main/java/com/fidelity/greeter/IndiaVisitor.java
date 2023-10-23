package com.fidelity.greeter;

public class IndiaVisitor implements Visitor {
	private String name;
	private String greeting;
	
	public IndiaVisitor(){
		System.out.println("created India visitor");
		this.name = "Swaroop";
		this.greeting = "Hello";
	}
	
	@Override
	public String toString() {
		return "IndiaVisitor [name=" + name + ", greeting=" + greeting + "]";
	}

	public String getGreeting() {
		return greeting;
	}

	public String getName() {
		return name;
	}
}
