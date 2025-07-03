package workbook.spring1.converter.user;


import workbook.spring1.domain.User;
import workbook.spring1.domain.enums.Address;
import workbook.spring1.web.dto.request.user.UserRequestDTO;
import workbook.spring1.web.dto.response.user.UserResponseDTO;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserConverter {

    public static UserResponseDTO.JoinResultDTO toJoinResultDTO(User user) {
        return UserResponseDTO.JoinResultDTO.builder()
                .userId(user.getId())
                .createAt(LocalDateTime.now())
                .build();
    }

    public static User toUser(UserRequestDTO.JoinDTO request) {
        Address address = new Address(request.getCity(), request.getStreet(), request.getZipcode());

        return User.builder()
                .userAddress(address)
                .gender(request.getGender())
                .userName(request.getUsername()) // 필드명 userName 에 맞춰야
                .userPreferList(new ArrayList<>())
                .build();

    }


}
