package workbook.spring1.validation.validator;


import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import workbook.spring1.apiPayload.code.status.ErrorStatus;
import workbook.spring1.service.StoreService.StoreQueryService;
import workbook.spring1.validation.annotation.ExistCategories;
import workbook.spring1.validation.annotation.ValidPage;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<ValidPage, Integer> {

    @Override
    public void initialize(ValidPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        System.out.println("PageValidator 실행됨: " + value); // 실행 안됨
        if (value == null) return true; // null이면 다른 @NotNull에서 걸러짐
        if (value < 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.PAGE_INVALID_PAGE_NUMBER.toString()).addConstraintViolation();
            return false;
        }

        return true;

    }


}
