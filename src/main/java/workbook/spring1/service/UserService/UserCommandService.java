package workbook.spring1.service.UserService;

import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.web.dto.request.user.UserRequestDTO;

public interface UserCommandService {
    public User joinUser(UserRequestDTO.JoinDTO request);
    public UserMission updateMissionStatus(Long MissionId, Long userId);
}
