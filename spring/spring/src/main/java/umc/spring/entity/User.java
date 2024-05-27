package umc.spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private Boolean userGender;

    @Column(nullable = false)
    private LocalDate userBirth;

    @Column(nullable = false)
    private String userAddress;

    @Column(nullable = false)
    private Long userPoint;

    @Column(nullable = false)
    private Boolean userStatus;

    //-------------------------------------------------------------------

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<UserMission> userMissions;

    @OneToMany(mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Review> reviews;

}
