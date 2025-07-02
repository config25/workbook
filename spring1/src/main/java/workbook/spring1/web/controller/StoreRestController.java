package workbook.spring1.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
import workbook.spring1.service.StoreService.StoreQueryService;
import workbook.spring1.validation.annotation.ExistMission;
import workbook.spring1.validation.annotation.ExistStore;
import workbook.spring1.validation.annotation.ExistUser;
import workbook.spring1.validation.annotation.ValidPage;
import workbook.spring1.web.dto.request.mission.MissionRequestDTO;
import workbook.spring1.web.dto.request.review.ReviewRequestDTO;
import workbook.spring1.web.dto.response.mission.MissionResponseDTO;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;
    private final StoreQueryService storeQueryService;

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


//    @GetMapping("/{storeId}/reviews")
//    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
//    @ApiResponses({
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
//    })
//    @Parameters({
//            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
//    })
//    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId){
//        return null;
//    }

    @Operation(summary = "특정 가게의 리뷰 목록 조회 API",description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/reviews")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistStore @PathVariable(name = "storeId") Long storeId, @ValidPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewPage = storeQueryService.getStoreReviewList(storeId,page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewPage));
    }

    @Operation(summary = "특정 가게의 미션 목록 조회 API",description = "특정 가게의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{storeId}/missions/{userId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.StoreMissionPreViewListDTO> getMissionList(@ExistStore @PathVariable(name = "storeId") Long storeId, @ExistUser @PathVariable(name = "userId") Long userId, @ValidPage @RequestParam(name = "page") Integer page){
        Page<Mission> MissionPage = storeQueryService.getStoreMissionList(storeId, page);
        return ApiResponse.onSuccess(MissionConverter.storeMissionPreViewListDTO(MissionPage, userId));
    }


}

