package com.ajira.Marsrover.demo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PatchEnvironment {

	private Integer temperature;
	
	private Integer humidity;
	
	@JsonProperty(value = "solar-flare")
	private Boolean solarFlare;
	
	private Boolean storm;

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
	
}
