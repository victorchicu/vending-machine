package challenge_1.web.validators.impl;

import challenge_1.web.validators.MultipleOf5;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class MultipleOf5Validator implements ConstraintValidator<MultipleOf5, Integer> {
    public static final Set<Integer> DENOMINATIONS = Set.of(5, 10, 20, 50, 100);

    @Override
    public boolean isValid(Integer amount, ConstraintValidatorContext context) {
        return isMultipleOf5(amount);
    }

    private static boolean isMultipleOf5(int n) {
        if ((n & 1) == 1)
            n <<= 1;
        float x = n;
        x = (int) (x * 0.1) * 10;
        return (int) x == n;
    }
}