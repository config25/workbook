package workbook.spring1.converter.review;

import org.springframework.data.domain.Page;
import workbook.spring1.domain.mapping.Picture;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.web.dto.request.review.ReviewRequestDTO;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewPostResultDTO toReviewPostResultDTO(Review review) {
        return ReviewResponseDTO.ReviewPostResultDTO.builder()
                .ReviewId(review.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static Review ToReview(ReviewRequestDTO.ReviewPostDTO request) {
        Review review = Review.builder()
                .rating(request.getRating())
                .content(request.getContent())
                .pictureList(new ArrayList<>())  // 빈 리스트 먼저 초기화
                .build();

        // PictureDTO → Picture 변환
        if (request.getReviewPictureList() != null) {
            List<Picture> pictures = request.getReviewPictureList().stream()
                    .map(dto -> Picture.builder()
                            .pictureUrl(dto.getPictureUrl())
                            .review(review)  // 연관관계 설정
                            .build())
                    .collect(Collectors.toList());
            review.getPictureList().addAll(pictures);
        }

        return review;
    }


    public static ReviewResponseDTO.ReviewPreViewDTO reviewPreViewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreViewDTO.builder()
                .username(review.getUser().getUserName())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toLocalDate())
                .content(review.getContent())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreViewDTO> reviewPreViewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreViewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreViewDTOList.size())
                .reviewList(reviewPreViewDTOList)
                .build();

    }


}
