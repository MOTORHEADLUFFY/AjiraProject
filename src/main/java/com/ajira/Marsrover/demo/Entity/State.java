package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ajira.Marsrover.demo.Utility.EnumCheckerArray;

public class State {

	@Valid
	@NotBlank(message = "Name can not be null")
	private String name;
	
	@Valid
	@NotNull(message = "Allowed Actions can not be null")
	@EnumCheckerArray(anyOf = {"collect-sample", "move"})
	private String[] allowedActions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getAllowedActions() {
		return allowedActions;
	}

	public void setAllowedActions(String[] allowedActions) {
		this.allowedActions = allowedActions;
	}

}
