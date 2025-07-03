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
import workbook.spring1.converter.user.UserConverter;
import workbook.spring1.domain.Mission;
import workbook.spring1.domain.User;
import workbook.spring1.domain.mapping.Review;
import workbook.spring1.domain.mapping.UserMission;
import workbook.spring1.service.UserService.UserCommandService;
import workbook.spring1.service.UserService.UserQueryService;
import workbook.spring1.validation.annotation.ExistMission;
import workbook.spring1.validation.annotation.ExistUser;
import workbook.spring1.validation.annotation.ValidPage;
import workbook.spring1.web.dto.request.user.UserRequestDTO;
import workbook.spring1.web.dto.response.mission.MissionResponseDTO;
import workbook.spring1.web.dto.response.review.ReviewResponseDTO;
import workbook.spring1.web.dto.response.user.UserResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserRestController {

    private final UserCommandService userCommandService;
    private final UserQueryService userQueryService;

    @PostMapping("/")
    public ApiResponse<UserResponseDTO.JoinResultDTO> join(@RequestBody @Valid UserRequestDTO.JoinDTO request) {
        User user = userCommandService.joinUser(request);
        return ApiResponse.onSuccess(UserConverter.toJoinResultDTO(user));
    }


    @Operation(summary = "특정 사용자의 리뷰 목록 조회 API",description = "특정 사용자의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{userId}/reviews")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreViewListDTO> getReview(@ExistUser @PathVariable(name = "userId") Long userId, @ValidPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewPage = userQueryService.getUserReviewList(userId,page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreViewListDTO(reviewPage));
    }

    @Operation(summary = "특정 사용자의 미션 목록 조회 API",description = "특정 사용자의 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @GetMapping("/{userId}/missions")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.UserMissionPreViewListDTO> getMission(@ExistUser @PathVariable(name = "userId") Long userId, @ValidPage @RequestParam(name = "page") Integer page){
        Page<UserMission> MissionPage = userQueryService.getUserMissionList(userId,page);
        return ApiResponse.onSuccess(MissionConverter.userMissionPreViewListDTO(MissionPage));
    }


    @Operation(summary = "특정 사용자의 진행중인 미션 진행완료로 변경 및 조회 API",description = "특정 사용자의 미션을 변경하고 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @PostMapping("/{userId}/missions/{missionId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "userId", description = "사용자의 아이디, path variable 입니다!"),
            @Parameter(name = "missionId", description = "미션의 아이디, path variable 입니다!"),
            @Parameter(name = "page", description = "페이지 번호, 0번이 1 페이지 입니다."),
    })
    public ApiResponse<MissionResponseDTO.MissionStatusPreviewListDTO> updateMissionStatusAndGetUserMissions(@ExistUser @PathVariable(name = "userId") Long userId,
                                                                                         @ExistMission @PathVariable(name = "missionId") Long missionId,
                                                                                         @ValidPage @RequestParam(name = "page") Integer page){
        userCommandService.updateMissionStatus(missionId, userId);
        Page<UserMission> MissionPage = userQueryService.getUserMissionList(userId, page);
        return ApiResponse.onSuccess(MissionConverter.missionStatusPreviewListDTO(MissionPage));

    }



}
