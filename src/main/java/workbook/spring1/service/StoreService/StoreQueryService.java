package workbook.spring1.service.StoreService;

import org.springframework.data.domain.Page;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.mapping.Review;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long storeId);

    Page<Review> getStoreReviewList(Long storeId, Integer page);

    Page<Mission> getStoreMissionList(Long storeId, Integer page);

}
