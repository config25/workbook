package workbook.spring1.domain;

import workbook.spring1.domain.common.BaseEntity;
import workbook.spring1.domain.enums.InquiryCategory;
import workbook.spring1.domain.mapping.Picture;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Inquiry extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private InquiryCategory type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "inquiry", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Picture> pictureList = new ArrayList<>();

}
