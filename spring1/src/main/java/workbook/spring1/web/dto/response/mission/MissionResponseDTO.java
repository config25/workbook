package workbook.spring1.web.dto.response.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionPostResultDTO {
        Long missionId;
        LocalDateTime createAt;
    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionGetResultDTO {
        Long UserMissionId;
        LocalDateTime createAt;
    }




}
