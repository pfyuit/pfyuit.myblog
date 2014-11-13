package com.pfyuit.myblog.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@NotNull
@NotEmpty
@Constraint(validatedBy = { FormatValidator.class })
public @interface Format {

	String message() default "format must be XML or JSON";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
