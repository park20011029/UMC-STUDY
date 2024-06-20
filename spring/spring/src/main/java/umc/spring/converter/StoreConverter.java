package umc.spring.converter;

import org.springframework.stereotype.Component;
import umc.spring.dto.request.AddReviewDto;
import umc.spring.dto.request.AddStoreDto;
import umc.spring.entity.*;

@Component
public class StoreConverter {

    public Store toStore(AddStoreDto addStoreDto, Region region) {
        return Store.builder()
                .storeName(addStoreDto.getStoreName())
                .storeAddress(addStoreDto.getAddress())
                .regionId(region)
                .build();
    }

    public Review toReview(AddReviewDto addReviewDto, Store store, User user) {
        return Review.builder()
                .userId(user)
                .reviewContent(addReviewDto.getReviewContent())
                .reviewStar(addReviewDto.getReviewStar())
                .storeId(store)
                .build();
    }

    public UserMission toUserMission(Mission mission, User user) {
        return UserMission.builder()
                .missionId(mission)
                .userId(user)
                .missionStatus(MissionStatus.PROGRESS)
                .build();
    }
}

