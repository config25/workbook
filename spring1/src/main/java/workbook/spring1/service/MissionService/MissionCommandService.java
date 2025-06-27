package workbook.spring1.service.MissionService;

import workbook.spring1.domain.Mission;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission createMission(Long storeId, MissionRequestDTO.MissionPostDTO request);

    UserMission getUserMission(Long storeId, Long userId, Long missionId, MissionRequestDTO.MissionGetDTO request);

}
