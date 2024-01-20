package com.qpassessment.util;

import com.qpassessment.annotation.EnumValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumValidatorPattern implements ConstraintValidator<EnumValidation, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        return acceptedValues.contains(value.toString());
    }
}
