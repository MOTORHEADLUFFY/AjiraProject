package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.ajira.Marsrover.demo.Utility.EnumChecker2DArray;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnvironmentConfiguration {

	@Valid
	@NotNull(message = "Temperature can not be null")
	private Integer temperature;
	
	@Valid
	@NotNull(message = "Humidity can not be null")
	private Integer humidity;
	
	@Valid
	@NotNull(message = "Solar Flare can not be null")
	@JsonProperty(value = "solar-flare")
	private Boolean solarFlare;
	
	@Valid
	@NotNull(message = "Storm can not be null")
	private Boolean storm;
	
	@Valid
	@NotNull(message = "Please Enter the map configuration")
	@JsonProperty(value = "area-map")
	@EnumChecker2DArray(anyOf = {"dirt", "water", "rock", "sand"})
	private String[][] areaMap;

	public Integer getTemperature() {
		return temperature;
	}

	public void setTemperature(Integer temperature) {
		this.temperature = temperature;
	}

	public Integer getHumidity() {
		return humidity;
	}

	public void setHumidity(Integer humidity) {
		this.humidity = humidity;
	}

	public Boolean getSolarFlare() {
		return solarFlare;
	}

	public void setSolarFlare(Boolean solarFlare) {
		this.solarFlare = solarFlare;
	}

	public Boolean getStorm() {
		return storm;
	}

	public void setStorm(Boolean storm) {
		this.storm = storm;
	}

	public String[][] getAreaMap() {
		return areaMap;
	}

	public void setAreaMap(String[][] areaMap) {
		this.areaMap = areaMap;
	}
	
	
}
