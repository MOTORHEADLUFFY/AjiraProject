package com.ajira.Marsrover.demo.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ajira.Marsrover.demo.Constants.Constants;
import com.ajira.Marsrover.demo.Database.DatabaseService;
import com.ajira.Marsrover.demo.Entity.EnvironmentConfiguration;
import com.ajira.Marsrover.demo.Entity.PatchEnvironment;
import com.ajira.Marsrover.demo.Entity.Response;
import com.ajira.Marsrover.demo.Service.CommonService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*")
@RequestMapping("api/environment")
@RestController
public class EnvironmentController {

	@Autowired
	CommonService commonService;

	@PatchMapping("")
	@ResponseBody
	@ApiOperation(value = "Update Environment Variables")
	public ResponseEntity<Response> patchEnviroment(@Valid @RequestBody PatchEnvironment patch) {
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
			return commonService.applyPatch(patch);
		}
	}

	@PostMapping("/configure")
	@ResponseBody
	@ApiOperation(value = "Set Initial Configuration For Environment")
	public ResponseEntity<Response> setInitialEnviromentConfig(
			@Valid @RequestBody EnvironmentConfiguration initialConfig) {
		Response response = null;
		if (DatabaseService.environmentConfiguration == null) {
			DatabaseService.environmentConfiguration = initialConfig;
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

	@GetMapping("/resetAll")
	@ResponseBody
	@ApiOperation(value = "Reset All Previously Set Configurations")
	public ResponseEntity<Response> resetAllConfigurations() {
		Response response = null;
		try {
			DatabaseService.environmentConfiguration = null;
			DatabaseService.roverConfiguration = null;

			response = new Response();
			response.setResponseCode(Constants.SUCCESS_CODE);
			response.setResponseMessage(Constants.RESET_SUCCESSFUL);
			return new ResponseEntity<Response>(response, HttpStatus.OK);
		} catch (Exception ex) {
			response = new Response();
			response.setResponseCode(Constants.ERROR_CODE);
			response.setResponseMessage(Constants.RESET_FAILURE);
			return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
