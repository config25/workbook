package workbook.spring1.web.dto.response.mission;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import workbook.spring1.domain.enums.MissionStatus;
import workbook.spring1.domain.enums.StoreCategory;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionPreViewListDTO {
        List<MissionResponseDTO.StoreMissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StoreMissionPreViewDTO {
        String storeName;
        String content;
        MissionStatus missionStatus;
        int missionPoint;


    }


    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewListDTO {
        List<MissionResponseDTO.UserMissionPreViewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMissionPreViewDTO {
        String storeName;
        StoreCategory storeCategory;
        LocalDateTime deadline;
        MissionStatus missionStatus;
        int missionPoint;


    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStatusPreviewListDTO{
        List<MissionResponseDTO.MissionStatusPreviewDTO> missionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MissionStatusPreviewDTO{
        String storeName;
        String content;
        MissionStatus missionStatus;
        int missionPoint;
    }





}
