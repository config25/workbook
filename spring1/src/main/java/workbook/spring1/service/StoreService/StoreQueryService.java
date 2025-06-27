package workbook.spring1.service.StoreService;

import workbook.spring1.domain.Store;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long storeId);
}
