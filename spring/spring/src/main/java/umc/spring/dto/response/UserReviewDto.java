package umc.spring.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserReviewDto {
    private String storeName;
    private String reviewContent;
    private Integer reviewStar;
}
