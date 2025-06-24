package workbook.spring1.domain;

import workbook.spring1.domain.common.BaseEntity;
import workbook.spring1.domain.enums.Address;
import workbook.spring1.domain.enums.Gender;
import workbook.spring1.domain.enums.LikeFood;
import workbook.spring1.domain.enums.UserStatus;
import workbook.spring1.domain.mapping.MemberMission;
import workbook.spring1.domain.mapping.Review;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String userName;

    @Column(columnDefinition = "VARCHAR(10)")
    @Enumerated(EnumType.STRING)
    private Gender gender;


    @Embedded
    private Address userAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private LikeFood likeFood;

    @Column
    private LocalDate birth;

    @Column(length = 50)
    private String userEmail;

    @Column(length = 20)
    private String PhoneNumber;

    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(nullable = false)
    private int userPoint;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Inquiry> inquiryList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Notification> notificationList = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private SocialLoggedIn socialLoggedIn;
}
