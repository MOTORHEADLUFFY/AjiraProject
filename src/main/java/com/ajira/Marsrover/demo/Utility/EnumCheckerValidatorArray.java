package com.ajira.Marsrover.demo.Utility;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumCheckerValidatorArray implements ConstraintValidator<EnumCheckerArray, String[]> {
	
	private String[] list;

	@Override
	public boolean isValid(String[] values, ConstraintValidatorContext context) {
		boolean answer = true;
		for (String value: values) {
			answer = answer & Arrays.asList(list).contains(value);
		}
		return answer;
	}
	
    @Override  
    public void initialize(EnumCheckerArray constraint) {
       this.list = constraint.anyOf();
    }

}
