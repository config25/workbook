package workbook.spring1.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import workbook.spring1.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = CategoriesExistValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}