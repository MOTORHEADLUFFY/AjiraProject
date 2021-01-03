package com.ajira.Marsrover.demo.Service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ajira.Marsrover.demo.Constants.Constants;
import com.ajira.Marsrover.demo.Database.DatabaseService;
import com.ajira.Marsrover.demo.Entity.Environment;
import com.ajira.Marsrover.demo.Entity.InventoryItem;
import com.ajira.Marsrover.demo.Entity.Location;
import com.ajira.Marsrover.demo.Entity.PatchEnvironment;
import com.ajira.Marsrover.demo.Entity.Response;
import com.ajira.Marsrover.demo.Entity.Rover;
import com.ajira.Marsrover.demo.Entity.RoverStatistics;
import com.ajira.Marsrover.demo.Entity.RoverStatus;
import com.ajira.Marsrover.demo.Entity.Scenario;
import com.ajira.Marsrover.demo.Entity.ScenarioCondition;

@Service
public class CommonService {

	public ResponseEntity<Response> moveRover(String direction) {
		Response response = null;
		if (DatabaseService.roverConfiguration.getInitialBattery() == 0
				|| DatabaseService.roverConfiguration.getInitialBattery() == -1) {

			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.DEMO_PROJECT_ASSUMPTION);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

//			For the purpose of this demo and running on Heroku, I am not shutting down the rover.
//			System.exit(0);
		} else {
			if (DatabaseService.environmentConfiguration.getStorm() == true) {
				response = new Response();
				response.setResponseCode(Constants.ERROR_CODE);
				response.setResponseMessage(Constants.STORM_ERROR);
				return new ResponseEntity<Response>(response, HttpStatus.PRECONDITION_REQUIRED);
			}

			boolean isValid = true;
			switch (direction) {
			case "up":
				if (DatabaseService.roverConfiguration.getDeployPoint().getRow() > 0) {
					DatabaseService.roverConfiguration.getDeployPoint()
							.setRow(DatabaseService.roverConfiguration.getDeployPoint().getRow() - 1);
				} else {
					isValid = false;
				}
				break;
			case "down":
				if (DatabaseService.roverConfiguration.getDeployPoint()
						.getRow() < DatabaseService.environmentConfiguration.getAreaMap().length - 1) {
					DatabaseService.roverConfiguration.getDeployPoint()
							.setRow(DatabaseService.roverConfiguration.getDeployPoint().getRow() + 1);
				} else {
					isValid = false;
				}
				break;
			case "left":
				if (DatabaseService.roverConfiguration.getDeployPoint().getColumn() > 0) {
					DatabaseService.roverConfiguration.getDeployPoint()
							.setColumn(DatabaseService.roverConfiguration.getDeployPoint().getColumn() - 1);
				} else {
					isValid = false;
				}
				break;
			case "right":
				if (DatabaseService.roverConfiguration.getDeployPoint()
						.getColumn() < DatabaseService.environmentConfiguration
								.getAreaMap()[DatabaseService.roverConfiguration.getDeployPoint().getRow()].length
								- 1) {
					DatabaseService.roverConfiguration.getDeployPoint()
							.setColumn(DatabaseService.roverConfiguration.getDeployPoint().getColumn() + 1);
				} else {
					isValid = false;
				}
				break;
			default:
			}

			if (isValid) {
				response = new Response();
				response.setResponseCode(Constants.SUCCESS_CODE);
				response.setResponseMessage(Constants.MOVE_SUCCESS);
				DatabaseService.roverConfiguration
						.setInitialBattery(DatabaseService.roverConfiguration.getInitialBattery() - 1);
				DatabaseService.steps++;
				if (DatabaseService.steps % 10 == 0) {
					DatabaseService.roverConfiguration.setInitialBattery(Constants.RECHARGE_BATTERY);
				}
				return new ResponseEntity<Response>(response, HttpStatus.OK);
			} else {
				response = new Response();
				response.setResponseCode(Constants.ERROR_CODE);
				response.setResponseMessage(Constants.MOVE_ERROR);
				return new ResponseEntity<Response>(response, HttpStatus.PRECONDITION_REQUIRED);
			}

		}
	}

	public ResponseEntity<RoverStatus> getRoverStatus() {
		RoverStatus roverStatus = null;

		roverStatus = new RoverStatus();
		roverStatus.setRover(new RoverStatistics());
		roverStatus.getRover().setLocation(new Location());
		roverStatus.setEnvironment(new Environment());
		roverStatus.getRover().getLocation().setRow(DatabaseService.roverConfiguration.getDeployPoint().getRow() + 1);
		roverStatus.getRover().getLocation()
				.setColumn(DatabaseService.roverConfiguration.getDeployPoint().getColumn() + 1);
		roverStatus.getRover().setBattery(DatabaseService.roverConfiguration.getInitialBattery());
		roverStatus.getRover().setInventory(DatabaseService.roverConfiguration.getInventory());
		roverStatus.getEnvironment().setHumidity(DatabaseService.environmentConfiguration.getHumidity());
		roverStatus.getEnvironment().setTemperature(DatabaseService.environmentConfiguration.getTemperature());
		roverStatus.getEnvironment().setSolarFlare(DatabaseService.environmentConfiguration.getSolarFlare());
		roverStatus.getEnvironment().setStorm(DatabaseService.environmentConfiguration.getStorm());
		roverStatus.getEnvironment()
				.setTerrain(DatabaseService.environmentConfiguration.getAreaMap()[DatabaseService.roverConfiguration
						.getDeployPoint().getRow()][DatabaseService.roverConfiguration.getDeployPoint().getColumn()]);
		return new ResponseEntity<RoverStatus>(roverStatus, HttpStatus.OK);
	}

	public ResponseEntity<Response> applyPatch(PatchEnvironment patch) {
		Response response = null;
		try {
			if (patch.getTemperature() != null) {
				DatabaseService.environmentConfiguration.setTemperature(patch.getTemperature());
			}

			if (patch.getHumidity() != null) {
				DatabaseService.environmentConfiguration.setHumidity(patch.getHumidity());
			}

			if (patch.getSolarFlare() != null) {
				DatabaseService.environmentConfiguration.setSolarFlare(patch.getSolarFlare());
				DatabaseService.roverConfiguration.setInitialBattery(11);
			}

			if (patch.getStorm() != null) {
				boolean roverKnowsToUseShields = false;
				boolean roverHasAStormShield = false;
				DatabaseService.environmentConfiguration.setStorm(patch.getStorm());
				if (patch.getStorm()) {
					for (Scenario scenario : DatabaseService.roverConfiguration.getScenarios()) {
						System.out.println(scenario.getName());
						if (checkStorm(scenario.getConditions())) {
							for (Rover rover : scenario.getRover()) {
								if (rover.getPerforms() != null) {
									if (rover.getPerforms().getItemUsage() != null) {
										if (rover.getPerforms().getItemUsage().getType().equals("storm-shield")
												&& rover.getPerforms().getItemUsage().getQty() > 0) {
											roverKnowsToUseShields = true;
										}
									}
								}
							}
						}
					}
					for (InventoryItem item : DatabaseService.roverConfiguration.getInventory()) {
						System.out.println(item.getType());
						if (item.getType().equals("storm-shield")) {
							roverHasAStormShield = true;
						}
					}

					if ((roverHasAStormShield & roverKnowsToUseShields) == false) {

						DatabaseService.roverConfiguration.setInitialBattery(-1); // -1 represents Rover is Destroyed

						response = new Response();
						response.setResponseCode(Constants.ERROR_CODE);
						response.setResponseMessage(Constants.DEMO_PROJECT_ASSUMPTION);
						return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);

//						For the purpose of this demo and running on Heroku, I am not shutting down the rover.
//						System.exit(0);
					}

				}
			}

			response = new Response();
			response.setResponseCode(Constants.SUCCESS_CODE);
			response.setResponseMessage(Constants.OPERATION_SUCCESSFUL);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception ex) {
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.OPERATION_FAILED);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}

	public boolean checkStorm(ScenarioCondition[] scenarioConditions) {
		for(ScenarioCondition sc: scenarioConditions) {
			if(sc.getProperty().equals("storm"))
				return true;
		}
		return false;
	}
}
