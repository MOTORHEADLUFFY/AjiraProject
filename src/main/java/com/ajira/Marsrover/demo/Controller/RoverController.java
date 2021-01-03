package com.ajira.Marsrover.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajira.Marsrover.demo.Constants.Constants;
import com.ajira.Marsrover.demo.Database.DatabaseService;
import com.ajira.Marsrover.demo.Entity.Direction;
import com.ajira.Marsrover.demo.Entity.Response;
import com.ajira.Marsrover.demo.Entity.RoverConfiguration;
import com.ajira.Marsrover.demo.Service.CommonService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("api/rover")
@RestController
public class RoverController {

	@Autowired
	CommonService commonService;

	@PostMapping("/configure")
	@ResponseBody
	@ApiOperation(value = "Set Initial Configuration for Rover")
	public ResponseEntity<Response> setInitialRoverConfig(@Valid @RequestBody RoverConfiguration initialConfig) {
		Response response = null;
		if (DatabaseService.roverConfiguration == null) {

			initialConfig.getDeployPoint().setRow(initialConfig.getDeployPoint().getRow() - 1);
			initialConfig.getDeployPoint().setColumn(initialConfig.getDeployPoint().getColumn() - 1);
			DatabaseService.roverConfiguration = initialConfig;

			response = new Response();
			response.setResponseCode(Constants.SUCCESS_CODE);
			response.setResponseMessage(Constants.INITIAL_CONFIGURATION_SET_SUCCESS);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.INITIAL_CONFIGURATION_ALREADY_SET_ERROR);
			return new ResponseEntity<Response>(response, HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

	@PostMapping("/move")
	@ResponseBody
	@ApiOperation(value = "Move The Rover")
	public ResponseEntity<Response> moveRover(@Valid @RequestBody Direction direction) {
		Response response = null;
		if (DatabaseService.environmentConfiguration == null || DatabaseService.roverConfiguration == null) {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.SET_INITIAL_CONFIGURATION_ERROR);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else if (DatabaseService.roverConfiguration.getInitialBattery() <= 0) {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.DEMO_PROJECT_ASSUMPTION);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			return commonService.moveRover(direction.getDirection());
		}
	}

	@GetMapping("/status")
	@ResponseBody
	@ApiOperation(value = "Get Status of the Rover")
	public Object getRoverStatus() {
		Response response = null;
		if (DatabaseService.environmentConfiguration == null || DatabaseService.roverConfiguration == null) {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.SET_INITIAL_CONFIGURATION_ERROR);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else if (DatabaseService.roverConfiguration.getInitialBattery() <= 0) {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.DEMO_PROJECT_ASSUMPTION);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		} else {
			return commonService.getRoverStatus();
		}
	}

}
