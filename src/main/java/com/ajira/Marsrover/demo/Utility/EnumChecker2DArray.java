package com.ajira.Marsrover.demo.Utility;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;

import javax.validation.Constraint;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumCheckerValidator2DArray.class)
@NotNull(message = "Value cannot be null")
@ReportAsSingleViolation
public @interface EnumChecker2DArray {
    String[] anyOf();
    String message() default "Incorrect value, must be anyOf {anyOf}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}