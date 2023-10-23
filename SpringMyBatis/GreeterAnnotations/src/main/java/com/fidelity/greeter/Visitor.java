package com.fidelity.greeter;

public interface Visitor {
	/* Visitor's name */
	public String getName();
	/* Hello, Howdy, Hiya, etc. */
	public String getGreeting();
	
	public void setName(String name);
	public void setGreeting(String greeting);
}
