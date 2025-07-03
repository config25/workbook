package workbook.spring1.web.dto.request.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import workbook.spring1.domain.enums.Gender;
import workbook.spring1.validation.annotation.ExistCategories;

import java.time.LocalDate;
import java.util.List;

public class UserRequestDTO {
    @Getter
    public static class JoinDTO {
        @NotBlank
        String username;
        @NotNull
        Gender gender;
        @NotNull
        LocalDate birthday;
        @Size(min = 5, max = 12)
        String city;
        @Size(min = 5, max = 12)
        String street;
        @Size(min = 5, max = 12)
        String zipcode;
        @ExistCategories
        private List<Long> preferCategory; // Enum 리스트


    }


}
