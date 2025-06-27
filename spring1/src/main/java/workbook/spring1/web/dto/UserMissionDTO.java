package workbook.spring1.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workbook.spring1.domain.enums.MissionStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserMissionDTO {
    @NotNull
    Long userId;

    @NotNull
    MissionStatus missionStatus;

}
