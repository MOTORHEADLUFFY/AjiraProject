package com.ajira.Marsrover.demo.Utility;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EnumCheckerValidator2DArray implements ConstraintValidator<EnumChecker2DArray, String[][]> {
	
	private String[] list;

	@Override
	public boolean isValid(String[][] rows, ConstraintValidatorContext context) {
		boolean answer = true;
		for (String[] row: rows) {
			for (String column: row) {
				answer = answer & Arrays.asList(list).contains(column);
			}
		}
		return answer;
	}
	
    @Override  
    public void initialize(EnumChecker2DArray constraint) {
       this.list = constraint.anyOf();
    }

}
