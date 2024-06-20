package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.dto.request.AddReviewDto;
import umc.spring.dto.request.AddStoreDto;
import umc.spring.entity.*;
import umc.spring.exception.ApiException;
import umc.spring.exception.ErrorDefine;
import umc.spring.repositroy.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final UserRepository userRepository;
    private final ReviewRepositroy reviewRepositroy;
    private final UserMissionRepository userMissionRepository;
    private final MissionRepository missionRepository;

    public Boolean addStore(AddStoreDto addStoreDto) {
        Region region = regionRepository.findByRegionName(addStoreDto.getRegionName())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Store newStore = Store.builder()
                .storeName(addStoreDto.getStoreName())
                .storeAddress(addStoreDto.getAddress())
                .regionId(region)
                .build();
        storeRepository.save(newStore);

        return true;
    }

    public Boolean addReview(AddReviewDto addReviewDto) {
        Store store = storeRepository.findByStoreName(addReviewDto.getStoreName())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        User user = userRepository.findById(addReviewDto.getUserId())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Review newReview = Review.builder()
                .userId(user)
                .reviewContent(addReviewDto.getReviewContent())
                .reviewStar(addReviewDto.getReviewStar())
                .storeId(store)
                .build();
        reviewRepositroy.save(newReview);

        return true;
    }

    public Boolean addUserMission(Long missionId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));

        UserMission newUserMission = UserMission.builder()
                .missionId(mission)
                .userId(user)
                .missionStatus(MissionStatus.PROGRESS)
                .build();
        userMissionRepository.save(newUserMission);
        return  true;
    }


}
