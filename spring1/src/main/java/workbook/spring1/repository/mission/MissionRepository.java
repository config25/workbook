package workbook.spring1.repository.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
