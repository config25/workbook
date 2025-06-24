package workbook.spring1.domain;

import workbook.spring1.domain.common.BaseEntity;
import workbook.spring1.domain.enums.Address;
import workbook.spring1.domain.enums.StoreCategory;
import workbook.spring1.domain.mapping.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String storeName;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private StoreCategory storeCategory;

    @Embedded
    private Address StoreAddress;

    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime openedDate;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Menu> menuList = new ArrayList<>();

}
