package workbook.spring1.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import workbook.spring1.apiPayload.code.BaseErrorCode;
import workbook.spring1.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),
//    _NOTFOUND(HttpStatus.NOT)

    // 멤버 관려 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "닉네임은 필수 입니다."),
    // 음식 관련
    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "Food4041", "음식을 찾을 수 없습니다"),

    // 가게 에러
    STORE_NOT_FOUND(HttpStatus.BAD_REQUEST, "STORE4001", "해당 가게가 없습니다."),


    // 미션 관련 에러
    MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "MISSION4001", "해당 미션이 없습니다."),
    MISSION_STATUS_INVALID(HttpStatus.BAD_REQUEST, "MISSION4002", "해당 미션은 진행중이지 않거나 이미 성공한 미션입니다."),

    // 유저미션 관련
    USER_MISSION_NOT_FOUND(HttpStatus.BAD_REQUEST, "USERMISSION4001", "해당 유저의 미션이 없습니다."),



    // Paging 관련 에러(음수일수 없음)
    PAGE_INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE4001", "페이지 번호는 0 이상의 정수여야 합니다."),



    // 예시,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");





    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }

}
