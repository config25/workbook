package workbook.spring1.repository.mission;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);

}
