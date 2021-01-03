package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Scenario {

	@NotBlank(message = "Name can not be null")
	private String name;
	
	@Valid
	@NotNull(message = "Conditions can not be null")
	private ScenarioCondition[] conditions;
	
	@Valid
	@NotNull(message = "Rover can not be null")
	private Rover[] rover;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ScenarioCondition[] getConditions() {
		return conditions;
	}

	public void setConditions(ScenarioCondition[] conditions) {
		this.conditions = conditions;
	}

	public Rover[] getRover() {
		return rover;
	}

	public void setRover(Rover[] rover) {
		this.rover = rover;
	}
	
}
