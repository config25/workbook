package workbook.spring1.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
}
