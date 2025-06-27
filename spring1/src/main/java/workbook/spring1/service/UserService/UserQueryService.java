package workbook.spring1.service.UserService;

import workbook.spring1.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> isUserExist(Long userId);
}
