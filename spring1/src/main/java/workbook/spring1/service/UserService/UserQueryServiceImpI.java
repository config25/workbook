package workbook.spring1.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.domain.User;
import workbook.spring1.repository.user.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserQueryServiceImpI implements UserQueryService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> isUserExist(Long userId){
        return userRepository.findById(userId);
    };


}
