package workbook.spring1.converter.mission;


import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.enums.MissionStatus;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;
import workbook.spring1.web.dto.response.mission.MissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionPostResultDTO MissionPostResultDTO(Mission mission) {
        return MissionResponseDTO.MissionPostResultDTO.builder()
                .missionId(mission.getId())
                .createAt(LocalDateTime.now())
                .build();

    }

    public static Mission ToMission(MissionRequestDTO.MissionPostDTO request, Store store) {
        return Mission.builder()
                .content(request.getContent())
                .missionPoint(request.getMissionPoint())
                .deadline(request.getDeadline())
                .store(store) // ⚠ 이게 반드시 있어야 함!
                .userMissionList(new ArrayList<>())
                .build();

//        List<UserMission> userMissions = users.stream().map(user ->
//                UserMission.builder()
//                        .missionStatus(MissionStatus.RUNNING)
//                        .user(user)
//                        .mission(mission)
//                        .build()
//        ).toList();
//
//        mission.getUserMissionList().addAll(userMissions);
//        return mission;

    }

    public static MissionResponseDTO.MissionGetResultDTO MissionGetResultDTO(UserMission userMission) {
        return MissionResponseDTO.MissionGetResultDTO.builder()
                .UserMissionId(userMission.getId())
                .createAt(LocalDateTime.now())
                .build();
    }


    public static UserMission ToUserMission(MissionRequestDTO.MissionGetDTO request, Store store, User user, Mission mission) {
        return UserMission.builder()
                .missionStatus(request.getMissionStatus())
                .mission(mission)
                .user(user)
                .build();
    }


}
