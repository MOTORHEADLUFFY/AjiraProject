package com.ajira.Marsrover.demo.Utility;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumCheckerValidator implements ConstraintValidator<EnumChecker, String> {
	
	private String[] list;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null || Arrays.asList(list).contains(value);
	}
	
    @Override  
    public void initialize(EnumChecker constraint) {
       this.list = constraint.anyOf();
    }

}
