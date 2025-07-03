package workbook.spring1.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.domain.Mission;
import workbook.spring1.repository.mission.MissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpI implements MissionQueryService {

    private MissionRepository missionRepository;

    @Override
    public Optional<Mission> findMission(Long missionId){
        return missionRepository.findById(missionId);
    }


}
