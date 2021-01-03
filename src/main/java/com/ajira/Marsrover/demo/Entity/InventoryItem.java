package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ajira.Marsrover.demo.Utility.EnumChecker;

public class InventoryItem {

	@Valid
	@NotBlank(message = "Type can not be null")
	@EnumChecker(anyOf = {"water-sample", "rock-sample", "storm-shield"})
	private String type;
	
	@Valid
	@NotNull(message = "Quantity can not be null")
	private Integer quantity;
	
	@Valid
	@NotNull(message = "Priority can not be null")
	private Integer priority;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	
}
