package com.pfyuit.myblog.validate;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FormatValidator implements ConstraintValidator<Format, String> {

	String[] methods = new String[] { "XML", "JSON" };

	@Override
	public void initialize(Format constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Arrays.asList(methods).contains(value);
	}

}
