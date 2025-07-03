package workbook.spring1.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import workbook.spring1.validation.validator.StoreExistValidator;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = StoreExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface ExistStore {
    String message() default "해당하는 가게가 존재하지 않음";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
