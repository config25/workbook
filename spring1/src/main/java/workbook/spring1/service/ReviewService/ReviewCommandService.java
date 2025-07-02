package workbook.spring1.service.ReviewService;

import workbook.spring1.domain.mapping.Review;
import workbook.spring1.web.dto.request.review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review createReview(Long userId, Long storeId, ReviewRequestDTO.ReviewPostDTO request);
}
