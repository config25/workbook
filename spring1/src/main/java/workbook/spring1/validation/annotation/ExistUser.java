package workbook.spring1.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import workbook.spring1.validation.validator.CategoriesExistValidator;
import workbook.spring1.validation.validator.UserExistsValidator;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserExistsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistUser {
    String message() default "해당하는 유저가 존재하지 않음";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
