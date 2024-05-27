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
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long missionReward;

    @Column(nullable = false)
    private LocalDate missionDeadline;

    //------------------------------------------

    @OneToMany(mappedBy = "missionId", fetch = FetchType.LAZY)
    private List<UserMission> userMissions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store storeId;
}
