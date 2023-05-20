package hr.fer.zpr.infsus.domain.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ComplexValidator implements ConstraintValidator<ComplexValidation, String> {

    private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
            "(?i).*\\b(DELETE|DROP|TRUNCATE|ALTER|SELECT|UPDATE|INSERT|EXEC|UNION|DECLARE|DATABASE|TABLE)\\b.*"
    );
    private static final int MIN_DESCRIPTION_LENGTH = 10;
    private static final Pattern VALID_DESCRIPTION_PATTERN = Pattern.compile("[A-Za-z0-9]+");
    @Override
    public void initialize(ComplexValidation constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        if (SQL_INJECTION_PATTERN.matcher(value).matches()) {
            return false;
        }
        return value.length() > MIN_DESCRIPTION_LENGTH && VALID_DESCRIPTION_PATTERN.matcher(value).matches();
    }
}