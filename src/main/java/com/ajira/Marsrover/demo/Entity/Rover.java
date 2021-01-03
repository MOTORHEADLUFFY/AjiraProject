package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;

public class Rover {

	@Valid
	private String is;
	
	@Valid
	private Performs performs;

	public String getIs() {
		return is;
	}

	public void setIs(String is) {
		this.is = is;
	}

	public Performs getPerforms() {
		return performs;
	}

	public void setPerforms(Performs performs) {
		this.performs = performs;
	}
	
	
}
