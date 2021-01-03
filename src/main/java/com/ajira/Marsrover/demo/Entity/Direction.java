package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import com.ajira.Marsrover.demo.Utility.EnumChecker;

public class Direction {

	@Valid
	@NotBlank(message = "Direction can not be null")
	@EnumChecker(anyOf = {"up", "down", "left", "right"})
	private String direction;

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
}
