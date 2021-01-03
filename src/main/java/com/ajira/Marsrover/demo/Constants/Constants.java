package com.ajira.Marsrover.demo.Constants;

public class Constants {

	public static final Integer ERROR_CODE  = 1;
	public static final Integer SUCCESS_CODE  = 0;
	public static final Integer RECHARGE_BATTERY  = 10;
	public static final String MOVE_SUCCESS  = "Rover moved Successfully";
	public static final String MOVE_ERROR  = "Can move only within mapped area";
	public static final String STORM_ERROR  = "Cannot move during a storm";
	public static final String SET_INITIAL_CONFIGURATION_ERROR = "Please Set Initial rover/Environment configs";
	public static final String INITIAL_CONFIGURATION_ALREADY_SET_ERROR  = "Initial config has already been set";
	public static final String INITIAL_CONFIGURATION_SET_SUCCESS = "Initial configuration set";
	public static final String OPERATION_SUCCESSFUL = "Operation successful";
	public static final String OPERATION_FAILED = "Operation Failed";
	public static final String RESET_SUCCESSFUL = "All configurations Reset successfully";
	public static final String RESET_FAILURE = "Reset Failure";
	public static final String DEMO_PROJECT_ASSUMPTION = "The Rover has been destroyed. For the Purpose of Keeping this app continously running on heroku,"
			+ " I am not shutting the application on battery Zero/Destruction. Please Assume the Application to be dead (System.exit(0) in java)";
}
