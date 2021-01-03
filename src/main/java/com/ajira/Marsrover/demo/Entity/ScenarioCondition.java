package com.ajira.Marsrover.demo.Entity;

import javax.validation.constraints.NotBlank;

import com.ajira.Marsrover.demo.Utility.EnumChecker;

public class ScenarioCondition {

	@NotBlank(message = "Type can not be null")
	@EnumChecker(anyOf = {"rover", "environment"})
	private String type;
	
	@NotBlank(message = "Property can not be null")
	@EnumChecker(anyOf = {"terrain", "battery", "temperature" ,"humidity", "solar-flare", "storm"})
	private String property;
	
	@NotBlank(message = "Operator can not be null")
	@EnumChecker(anyOf = {"eq", "ne", "lte", "gte", "lt", "gt"})
	private String operator;
	
	@NotBlank(message = "Value can not be null")
	private String value;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
}
