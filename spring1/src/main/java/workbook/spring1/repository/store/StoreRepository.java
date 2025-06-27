package workbook.spring1.repository.store;

import org.springframework.data.jpa.repository.JpaRepository;
import workbook.spring1.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
