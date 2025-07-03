package workbook.spring1.service.StoreService;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.Store;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.repository.Review.ReviewRepository;
import workbook.spring1.repository.mission.MissionRepository;
import workbook.spring1.repository.store.StoreRepository;
import workbook.spring1.repository.user.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpI implements StoreQueryService {

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long StoreId){
        return storeRepository.findById(StoreId);
    }

    @Override
    public Page<Review> getStoreReviewList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;

    }

    @Override
    public Page<Mission> getStoreMissionList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId).get();

        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return MissionPage;
    }





}
