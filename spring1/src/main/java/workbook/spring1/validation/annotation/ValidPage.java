package workbook.spring1.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import workbook.spring1.validation.validator.PageValidator;
import workbook.spring1.validation.validator.StoreExistValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PageValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPage {
    String message() default "페이지의 값은 양수여야 합니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
