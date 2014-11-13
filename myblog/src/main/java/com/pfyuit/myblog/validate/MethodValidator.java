package com.pfyuit.myblog.validate;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MethodValidator implements ConstraintValidator<Method, String> {

	String[] methods = new String[] { "GET", "POST", "DELETE", "PUT" };

	@Override
	public void initialize(Method constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Arrays.asList(methods).contains(value);
	}

}
