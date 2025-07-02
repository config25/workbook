package workbook.spring1.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import workbook.spring1.apiPayload.code.status.ErrorStatus;
import workbook.spring1.domain.User;
import workbook.spring1.service.UserService.UserCommandService;
import workbook.spring1.service.UserService.UserQueryService;
import workbook.spring1.validation.annotation.ExistUser;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<ExistUser, Long> {

    private final UserQueryService userQueryService;

    @Override
    public void initialize(ExistUser constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        Optional<User> target = userQueryService.isUserExist(value);

        if (target.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.USER_NOT_FOUND.toString()).addConstraintViolation();
            return false;
        }

        return true;

    }
}