package workbook.spring1.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import workbook.spring1.apiPayload.ApiResponse;
import workbook.spring1.converter.mission.MissionConverter;
import workbook.spring1.converter.review.ReviewConverter;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.service.MissionService.MissionCommandService;
import workbook.spring1.service.ReviewService.ReviewCommandService;
import workbook.spring1.validation.annotation.ExistMission;
import workbook.spring1.validation.annotation.ExistStore;
import workbook.spring1.validation.annotation.ExistUser;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;
import workbook.spring1.web.dto.request.review.ReviewRequestDTO;
import workbook.spring1.web.dto.response.mission.MissionResponseDTO;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewPostResultDTO> createReview(@RequestBody @Valid ReviewRequestDTO.ReviewPostDTO request,
                                                                           @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                           @ExistUser @RequestParam(name = "userId") Long userId) {
        Review review = reviewCommandService.createReview(userId, storeId, request);
        return ApiResponse.onSuccess(ReviewConverter.toReviewPostResultDTO(review));
    }


    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.MissionPostResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.MissionPostDTO request,
                                                                              @ExistStore @PathVariable(name = "storeId") Long storeId) {
        Mission mission = missionCommandService.createMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.MissionPostResultDTO(mission));
    }

    @PostMapping("/{storeId}/missions/{missionId}/challenge")
    public ApiResponse<MissionResponseDTO.MissionGetResultDTO> createMission(@RequestBody @Valid MissionRequestDTO.MissionGetDTO request,
                                                                              @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                              @ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                              @ExistUser @RequestParam (name = "userId") Long userId) {
        UserMission userMission = missionCommandService.getUserMission(storeId, userId, missionId, request);
        return ApiResponse.onSuccess(MissionConverter.MissionGetResultDTO(userMission));
    }



}
