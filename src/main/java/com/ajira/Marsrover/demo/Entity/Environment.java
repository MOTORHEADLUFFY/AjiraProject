package com.ajira.Marsrover.demo.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ajira.Marsrover.demo.Utility.EnumChecker;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Environment {

	@NotNull(message = "Temperature can not be null")
	private Integer temperature;
	
	@NotNull(message = "Humidity can not be null")
	private Integer humidity;
	
	@NotNull(message = "Solar Flare can not be null")
	@JsonProperty(value = "solar-flare")
	private Boolean solarFlare;
	
	@NotNull(message = "Storm can not be null")
	private Boolean storm;
	
	@NotBlank(message = "Storm can not be null")
	@EnumChecker(anyOf = {"dirt", "water", "rock", "sand"})
	private String terrain;

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

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}
	
}
