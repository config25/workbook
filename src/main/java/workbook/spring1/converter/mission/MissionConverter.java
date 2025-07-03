package workbook.spring1.converter.mission;


import org.springframework.data.domain.Page;
import workbook.spring1.converter.review.ReviewConverter;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.enums.MissionStatus;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;
import workbook.spring1.web.dto.response.mission.MissionResponseDTO;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


    public static MissionResponseDTO.StoreMissionPreViewDTO storeMissionPreViewDTO(Mission mission, Long userId) {

        // UserMission 중 userId에 해당하는 missionStatus 가져오기
        MissionStatus status = mission.getUserMissionList()
                .stream()
                .filter(um -> um.getUser().getId().equals(userId))
                .findFirst()
                .map(UserMission::getMissionStatus)
                .orElse(null); // 또는 기본값 설정 가능

        return MissionResponseDTO.StoreMissionPreViewDTO.builder()
                .storeName(mission.getStore().getStoreName())
                .content(mission.getContent())
                .missionStatus(status)
                .missionPoint(mission.getMissionPoint())
                .build();
    }

    public static MissionResponseDTO.StoreMissionPreViewListDTO storeMissionPreViewListDTO(Page<Mission> missionList, Long userId) {
        List<MissionResponseDTO.StoreMissionPreViewDTO> storeMissionPreViewDTOList = missionList.stream()
                .map(m -> storeMissionPreViewDTO(m, userId))
                .collect(Collectors.toList());

        return MissionResponseDTO.StoreMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(storeMissionPreViewDTOList.size())
                .missionList(storeMissionPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.UserMissionPreViewDTO userMissionPreViewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        return MissionResponseDTO.UserMissionPreViewDTO.builder()
                .storeName(mission.getStore().getStoreName())
                .storeCategory(mission.getStore().getStoreCategory())
                .deadline(mission.getDeadline())
                .missionStatus(userMission.getMissionStatus())
                .missionPoint(mission.getMissionPoint())
                .build();
    }

    public static MissionResponseDTO.UserMissionPreViewListDTO userMissionPreViewListDTO(Page<UserMission> missionList) {
        List<MissionResponseDTO.UserMissionPreViewDTO> userMissionPreViewDTOList = missionList.stream()
                .map(MissionConverter::userMissionPreViewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.UserMissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(userMissionPreViewDTOList.size())
                .missionList(userMissionPreViewDTOList)
                .build();
    }

    public static MissionResponseDTO.MissionStatusPreviewDTO missionStatusPreviewDTO(UserMission userMission) {
        Mission mission = userMission.getMission();

        return MissionResponseDTO.MissionStatusPreviewDTO.builder()
                .storeName(mission.getStore().getStoreName())
                .content(mission.getContent())
                .missionPoint(mission.getMissionPoint())
                .missionStatus(userMission.getMissionStatus())
                .build();
    }

    public static MissionResponseDTO.MissionStatusPreviewListDTO missionStatusPreviewListDTO(Page<UserMission> missionList) {
        List<MissionResponseDTO.MissionStatusPreviewDTO> missionStatusPreviewDTOList = missionList.stream()
                .map(MissionConverter::missionStatusPreviewDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.MissionStatusPreviewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionStatusPreviewDTOList.size())
                .missionList(missionStatusPreviewDTOList)
                .build();

    }




}
