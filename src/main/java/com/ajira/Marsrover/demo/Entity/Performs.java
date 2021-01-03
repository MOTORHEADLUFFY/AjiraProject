package com.ajira.Marsrover.demo.Entity;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Performs {

	@Valid
	@JsonProperty(value = "collect-sample")
	private CollectSample collectSample;
	
	@Valid
	@JsonProperty(value = "item-usage")
	private ItemUsage itemUsage;

	public CollectSample getCollectSample() {
		return collectSample;
	}

	public void setCollectSample(CollectSample collectSample) {
		this.collectSample = collectSample;
	}

	public ItemUsage getItemUsage() {
		return itemUsage;
	}

	public void setItemUsage(ItemUsage itemUsage) {
		this.itemUsage = itemUsage;
	}
	
	
}
