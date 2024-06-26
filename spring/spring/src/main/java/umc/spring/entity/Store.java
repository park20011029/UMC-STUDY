package umc.spring.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String storeName;

    @Column(nullable = false)
    private String storeAddress;

    //-------------------------------------------------
    @OneToMany(mappedBy = "storeId", fetch = FetchType.LAZY)
    private List<Mission> missions;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region regionId;

    @OneToMany(mappedBy = "storeId", fetch = FetchType.LAZY)
    private List<Review> reviews;

    @Builder
    public Store(String storeName, String storeAddress, Region regionId) {
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.regionId = regionId;
    }


}
