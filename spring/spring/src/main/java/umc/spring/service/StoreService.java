package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
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
    private final StoreConverter storeConverter;

    public Boolean addStore(AddStoreDto addStoreDto) {
        Region region = regionRepository.findByRegionName(addStoreDto.getRegionName())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Store newStore = storeConverter.toStore(addStoreDto, region);
        storeRepository.save(newStore);
        return true;
    }

    public Boolean addReview(AddReviewDto addReviewDto) {
        Store store = storeRepository.findByStoreName(addReviewDto.getStoreName())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        User user = userRepository.findById(addReviewDto.getUserId())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Review newReview = storeConverter.toReview(addReviewDto, store, user);
        reviewRepositroy.save(newReview);
        return true;
    }

    public Boolean addUserMission(Long missionId, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));
        UserMission newUserMission = storeConverter.toUserMission(mission, user);
        userMissionRepository.save(newUserMission);
        return true;
    }
}
