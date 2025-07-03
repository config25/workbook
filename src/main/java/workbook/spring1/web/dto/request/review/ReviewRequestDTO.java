package workbook.spring1.web.dto.request.review;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import workbook.spring1.domain.mapping.Picture;
import workbook.spring1.web.dto.request.PictureDTO;

import java.util.List;

public class ReviewRequestDTO {

    @Getter
    @Setter
    public static class ReviewPostDTO {
        @NotNull
        Long userId;

        @NotNull
        Long storeId;



        @NotNull
        double rating;

        String content;

        List<PictureDTO> ReviewPictureList;

    }


}
