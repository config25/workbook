package workbook.spring1.service.MissionService;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.apiPayload.code.status.ErrorStatus;
import workbook.spring1.apiPayload.exception.GeneralException;
import workbook.spring1.converter.mission.MissionConverter;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.repository.mission.MissionRepository;
import workbook.spring1.repository.mission.UserMissionRepository;
import workbook.spring1.repository.store.StoreRepository;
import workbook.spring1.repository.user.UserRepository;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpI implements MissionCommandService {

    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    private final UserMissionRepository userMissionRepository;

    @Override
    @Transactional
    public Mission createMission(Long storeId, MissionRequestDTO.MissionPostDTO request){
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));

//        List<User> users = request.getUserMissionsList().stream()
//                .map(dto -> userRepository.findById(dto.getUserId())
//                        .orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND))
//                ).toList();
        Mission mission = MissionConverter.ToMission(request, store);
        return missionRepository.save(mission);

    }

    @Override
    @Transactional
    public UserMission getUserMission(Long storeId, Long userId, Long missionId, MissionRequestDTO.MissionGetDTO request){
        Store store = storeRepository.findById(storeId).orElseThrow(() -> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        User user = userRepository.findById(userId).orElseThrow(() -> new GeneralException(ErrorStatus.USER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId).orElseThrow(() -> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        UserMission userMission = MissionConverter.ToUserMission(request, store, user, mission);
        return userMissionRepository.save(userMission);

    }



}
