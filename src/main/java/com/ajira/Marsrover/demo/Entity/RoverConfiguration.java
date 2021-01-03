package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoverConfiguration {

	@Valid
	@NotNull(message = "Scenarios can not be null")
	private Scenario[] scenarios;
	
	@Valid
	@NotNull(message = "State can not be null")
	private State[] states;
	
	@Valid
	@NotNull(message = "Deploy Point can not be null")
	@JsonProperty(value = "deploy-point")
	private DeployPoint deployPoint;
	
	@Valid
	@NotNull(message = "Initial Battery can not be null")
	@JsonProperty(value = "initial-battery")
	private Integer initialBattery;
	
	@Valid
	@NotNull(message = "Inventory can not be null")
	private InventoryItem[] inventory;

	public Scenario[] getScenarios() {
		return scenarios;
	}

	public void setScenarios(Scenario[] scenarios) {
		this.scenarios = scenarios;
	}

	public State[] getStates() {
		return states;
	}

	public void setStates(State[] states) {
		this.states = states;
	}

	public DeployPoint getDeployPoint() {
		return deployPoint;
	}

	public void setDeployPoint(DeployPoint deployPoint) {
		this.deployPoint = deployPoint;
	}

	public Integer getInitialBattery() {
		return initialBattery;
	}

	public void setInitialBattery(Integer initialBattery) {
		this.initialBattery = initialBattery;
	}

	public InventoryItem[] getInventory() {
		return inventory;
	}

	public void setInventory(InventoryItem[] inventory) {
		this.inventory = inventory;
	}
	
}
