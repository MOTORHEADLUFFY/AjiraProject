package com.ajira.Marsrover.demo.Database;

import org.springframework.stereotype.Service;

import com.ajira.Marsrover.demo.Entity.EnvironmentConfiguration;
import com.ajira.Marsrover.demo.Entity.RoverConfiguration;

@Service
public class DatabaseService {

	public static RoverConfiguration roverConfiguration = null;
	
	public static EnvironmentConfiguration environmentConfiguration = null;
	
	public static Integer steps = 0;
}
