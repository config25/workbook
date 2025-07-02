package workbook.spring1.service.MissionService;

import org.springframework.data.domain.Page;
import workbook.spring1.domain.Mission;

import java.util.Optional;

public interface MissionQueryService {
    Optional<Mission> findMission(Long missionId);

}
