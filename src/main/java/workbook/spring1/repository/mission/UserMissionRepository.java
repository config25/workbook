package workbook.spring1.repository.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.UserMission;

import java.util.Optional;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {
    Page<UserMission> findAllByUser(User user, PageRequest pageRequest);
    Optional<UserMission> findByUserIdAndMissionId(Long userId, Long missionId);
}
