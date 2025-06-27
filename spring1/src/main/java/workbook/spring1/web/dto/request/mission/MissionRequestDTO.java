package workbook.spring1.web.dto.request.mission;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import workbook.spring1.domain.enums.MissionStatus;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.web.dto.UserMissionDTO;

import java.time.LocalDateTime;
import java.util.List;

public class MissionRequestDTO {
    @Getter
    public static class MissionPostDTO {
        @NotNull
        Long storeId;

        @NotNull
        String content;

        int MissionPoint;

        @NotNull
        LocalDateTime deadline;

//        List<UserMissionDTO> userMissionsList;

    }
    @Getter
    public static class MissionGetDTO {
        @NotNull
        Long missionId;

        @NotNull
        Long userId;

        @NotNull
        MissionStatus missionStatus;


//        List<UserMissionDTO> userMissionsList;

    }



}
