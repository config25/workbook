package workbook.spring1.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import workbook.spring1.validation.validator.MissionExistValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = MissionExistValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface ExistMission {
    String message() default "해당 미션이 존재하지 않음";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
