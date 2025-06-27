package workbook.spring1.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import workbook.spring1.converter.review.ReviewConverter;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.repository.Review.ReviewRepository;
import workbook.spring1.repository.store.StoreRepository;
import workbook.spring1.repository.user.UserRepository;
import workbook.spring1.web.dto.request.review.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewCommandServiceImpI implements ReviewCommandService{

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Review createReview(Long userId, Long storeId, ReviewRequestDTO.ReviewPostDTO request) {
        Review review = ReviewConverter.ToReview(request);

        review.setUser(userRepository.findById(userId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);
    }



}
