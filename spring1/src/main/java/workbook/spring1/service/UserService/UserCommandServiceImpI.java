package workbook.spring1.service.UserService;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.apiPayload.code.status.ErrorStatus;
import workbook.spring1.apiPayload.exception.GeneralException;
import workbook.spring1.apiPayload.exception.handler.FoodCategoryHandler;
import workbook.spring1.converter.user.UserConverter;
import workbook.spring1.converter.UserPreferConverter;
import workbook.spring1.domain.FoodCategory;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.enums.MissionStatus;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.domain.mapping.UserPrefer;
import workbook.spring1.repository.FoodCategoryRepository;
import workbook.spring1.repository.mission.MissionRepository;
import workbook.spring1.repository.mission.UserMissionRepository;
import workbook.spring1.repository.user.UserRepository;
import workbook.spring1.web.dto.request.user.UserRequestDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserCommandServiceImpI implements UserCommandService {

    private final UserRepository userRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public User joinUser(UserRequestDTO.JoinDTO request) {

        User newUser = UserConverter.toUser(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() -> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<UserPrefer> userPreferList = UserPreferConverter.toUserPreferList(foodCategoryList);

        userPreferList.forEach(userPrefer -> {userPrefer.setUser(newUser);});

        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public UserMission updateMissionStatus(Long missionId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        UserMission userMission = userMissionRepository.findByUserIdAndMissionId(userId, missionId)
                .orElseThrow(() -> new GeneralException(ErrorStatus.USER_MISSION_NOT_FOUND));

        if (userMission.getMissionStatus() != MissionStatus.RUNNING) {
            throw new GeneralException(ErrorStatus.MISSION_STATUS_INVALID);
        }

        userMission.setMissionStatus(MissionStatus.SUCCESS);
        return userMissionRepository.save(userMission);


    }


}
