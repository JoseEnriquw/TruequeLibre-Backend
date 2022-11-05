package com.grupo3.truequelibre.tools;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = StringValidator.class)
@Documented
public @interface ValidateString {
	 	String[] acceptedValues();

	    String message() default "{uk.dds.ideskos.validator.ValidateString.message}";

	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { }; 
}
