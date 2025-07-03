package workbook.spring1.service.UserService;

import org.springframework.data.domain.Page;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {
    Optional<User> isUserExist(Long userId);
    Page<Review> getUserReviewList(Long userId, Integer page);
    Page<UserMission> getUserMissionList(Long userId, Integer page);
}
