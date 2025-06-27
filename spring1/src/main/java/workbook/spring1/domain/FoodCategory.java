package workbook.spring1.domain;

import jakarta.persistence.*;
import lombok.*;
import workbook.spring1.domain.common.BaseEntity;
import workbook.spring1.domain.mapping.UserPrefer;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FoodCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String FoodName;

    @OneToMany(mappedBy = "foodCategory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPrefer> UserPreferList = new ArrayList<>();

}