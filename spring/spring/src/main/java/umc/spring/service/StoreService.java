package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.dto.request.AddReviewDto;
import umc.spring.dto.request.AddStoreDto;
import umc.spring.dto.request.StoreIdRequestDto;
import umc.spring.dto.response.PageInfo;
import umc.spring.dto.response.UserMissionDto;
import umc.spring.dto.response.UserReviewDto;
import umc.spring.entity.*;
import umc.spring.exception.ApiException;
import umc.spring.exception.ErrorDefine;
import umc.spring.repositroy.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

    public Map<String, Object> storeMissionList(Integer page, Integer size, StoreIdRequestDto storeIdRequestDto) {
        Store store = storeRepository.findById(storeIdRequestDto.getStoreId())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));

        Pageable pageable = PageRequest.of(page -1, size);
        Page<Mission> selectMission = missionRepository.findByStoreId(store, pageable);

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(selectMission.getNumber() + 1)
                .totalPages(selectMission.getTotalPages())
                .pageSize(selectMission.getSize())
                .currentItems(selectMission.getNumberOfElements())
                .totalItems(selectMission.getTotalElements())
                .build();

        Map<String, Object> result = new HashMap<>();

        result.put("selectStoreMission",selectMission.getContent().stream()
                .map(missions-> UserMissionDto.builder()
                        .storeName(missions.getStoreId().getStoreName())
                        .missionReward(missions.getMissionReward())
                        .missionDeadline(missions.getMissionDeadline())
                        .build())
                .collect(Collectors.toList()));

        result.put("pageInfo",pageInfo);

        return result;
    }
}
