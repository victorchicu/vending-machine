package challenge_1.web.validators;

import challenge_1.web.validators.impl.MultipleOf5Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MultipleOf5Validator.class)
@Target( { ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MultipleOf5 {
    int[] DENOMINATIONS = new int[]{5, 10, 20, 50, 100};

    String message() default "Value is not a multiple of 5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
