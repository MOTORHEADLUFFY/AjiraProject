package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ajira.Marsrover.demo.Utility.EnumChecker;

public class CollectSample {

	@Valid
	@NotBlank(message = "Type can not be null")
	@EnumChecker(anyOf = {"water-sample", "rock-sample"})
	private String type;
	
	@Valid
	@NotNull(message = "Quantity can not be null")
	private Integer qty;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
}
