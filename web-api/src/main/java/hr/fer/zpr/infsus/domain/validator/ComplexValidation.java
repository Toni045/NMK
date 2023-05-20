package hr.fer.zpr.infsus.domain.validator;

import jakarta.validation.Payload;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ComplexValidator.class)
public @interface ComplexValidation {
    String message() default "Complex validation failed";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
