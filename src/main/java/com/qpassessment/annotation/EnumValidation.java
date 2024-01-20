package com.qpassessment.annotation;

import com.qpassessment.util.EnumValidatorPattern;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**@apiNote
 * Created generic annotation for enum validation from input request
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValidatorPattern.class)
public @interface EnumValidation {

    Class<? extends Enum<?>> enumClass();

    String message() default "must be any of enum {enumClass}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
