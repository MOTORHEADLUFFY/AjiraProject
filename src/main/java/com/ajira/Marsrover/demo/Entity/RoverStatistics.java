package com.ajira.Marsrover.demo.Entity;

public class RoverStatistics {

	private Location location;
	
	private Integer battery;
	
	private InventoryItem[] inventory;

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Integer getBattery() {
		return battery;
	}

	public void setBattery(Integer battery) {
		this.battery = battery;
	}

	public InventoryItem[] getInventory() {
		return inventory;
	}

	public void setInventory(InventoryItem[] inventory) {
		this.inventory = inventory;
	}
	
	
}
