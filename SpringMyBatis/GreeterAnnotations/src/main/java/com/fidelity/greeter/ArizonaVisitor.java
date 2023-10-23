package com.fidelity.greeter;

public class ArizonaVisitor implements Visitor {
	private String name;
	private String greeting;
	
	public ArizonaVisitor(){
		System.out.println("created Arizona visitor");
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

}
