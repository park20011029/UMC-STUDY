package umc.spring.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddReviewDto {
    private Long userId;
    private String storeName;
    private String reviewContent;
    private Integer reviewStar;
}
