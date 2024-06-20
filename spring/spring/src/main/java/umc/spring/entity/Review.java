package umc.spring.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private Integer reviewStar;

    //-----------------------------------------------

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store storeId;

    @Builder
    public Review(String reviewContent, Integer reviewStar, User userId, Store storeId) {
        this.reviewContent = reviewContent;
        this.reviewStar = reviewStar;
        this.userId = userId;
        this.storeId = storeId;
    }
}
