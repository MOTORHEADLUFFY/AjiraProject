package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DeployPoint {

	@Valid
	@NotNull(message = "Row Number can not be null")
	private Integer row;
	
	@Valid
	@NotNull(message = "Column Number can not be null")
	private Integer column;

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}
	
}
