package workbook.spring1.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.repository.Review.ReviewRepository;
import workbook.spring1.repository.mission.MissionRepository;
import workbook.spring1.repository.mission.UserMissionRepository;
import workbook.spring1.repository.user.UserRepository;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserQueryServiceImpI implements UserQueryService {

    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    public Optional<User> isUserExist(Long userId){
        return userRepository.findById(userId);
    };

    @Override
    public Page<Review> getUserReviewList(Long UserId, Integer page){
        User user = userRepository.findById(UserId).get();

        Page<Review> UserPage = reviewRepository.findAllByUser(user, PageRequest.of(page, 10));
        return UserPage;

    }


    @Override
    public Page<UserMission> getUserMissionList(Long UserId, Integer page){
        User user = userRepository.findById(UserId).get();

        Page<UserMission> UserPage = userMissionRepository.findAllByUser(user, PageRequest.of(page, 10));
        return UserPage;
    }


}
