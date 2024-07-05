package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.dto.request.UserIdDto;
import umc.spring.dto.response.PageInfo;
import umc.spring.dto.response.UserMissionDto;
import umc.spring.entity.MissionStatus;
import umc.spring.entity.User;
import umc.spring.entity.UserMission;
import umc.spring.exception.ApiException;
import umc.spring.exception.ErrorDefine;
import umc.spring.repositroy.UserMissionRepository;
import umc.spring.repositroy.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MissionService {
    private final UserMissionRepository userMissionRepository;
    private final UserRepository userRepository;

    public Map<String, Object> userMissionselect(Integer page, Integer size, UserIdDto userIdDto) {
        User user = userRepository.findById(userIdDto.getUserId())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));

        Pageable pageable = PageRequest.of(page -1, size);
        Page<UserMission> selectMission = userMissionRepository.findByUserIdAndMissionStatus(user, MissionStatus.PROGRESS, pageable);

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(selectMission.getNumber() + 1)
                .totalPages(selectMission.getTotalPages())
                .pageSize(selectMission.getSize())
                .currentItems(selectMission.getNumberOfElements())
                .totalItems(selectMission.getTotalElements())
                .build();

        Map<String, Object> result = new HashMap<>();

        result.put("selectUserMission",selectMission.getContent().stream()
                .map(missions-> UserMissionDto.builder()
                        .storeName(missions.getMissionId().getStoreId().getStoreName())
                        .missionReward(missions.getMissionId().getMissionReward())
                        .missionDeadline(missions.getMissionId().getMissionDeadline())
                        .missionReward(missions.getMissionId().getMissionReward())
                        .build())
                .collect(Collectors.toList()));

        result.put("pageInfo",pageInfo);

        return result;
    }
}
