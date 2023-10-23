package com.fidelity.greeter;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// TODO: add a Spring annotation that identifies the PopupGreeter class as a Spring Bean
//       with the ID "greeter"
// HINT: see slide 1-11

@Component("greeter")
public class PopupGreeter implements Greeter {
	// TODO: add an annotation that tells Spring to inject a Visitor
	//        bean into the following field.
	@Autowired
	@Qualifier("vis")
	private Visitor visitor;
	
	public Visitor getVisitor() {
		return visitor;
	}
	
	public void setVisitor(Visitor visitor) {
	    this.visitor = visitor;
	}

	public void greet() {
		String greeting = visitor.getGreeting();
		String name = visitor.getName();
		JOptionPane.showMessageDialog(null, greeting + ", " + name);
	}

	@Override
	public String toString() {
		return "PopupGreeter [visitor=" + visitor + "]";
	}
}
