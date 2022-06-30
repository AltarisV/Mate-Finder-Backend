package htw.berlin.webtech.matefinder.web.api;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.math.BigDecimal;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNumberValidator.class)
public @interface IsNumber {

    String message() default "Preis muss eine Nummer sein";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

class IsNumberValidator implements ConstraintValidator<IsNumber, String> {
    private IsNumber isNumber;

    @Override
    public void initialize(IsNumber constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.contains("[0-9.]");
    }
}
