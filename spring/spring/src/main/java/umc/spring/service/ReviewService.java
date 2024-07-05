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
import umc.spring.dto.response.UserReviewDto;
import umc.spring.entity.MissionStatus;
import umc.spring.entity.Review;
import umc.spring.entity.User;
import umc.spring.entity.UserMission;
import umc.spring.exception.ApiException;
import umc.spring.exception.ErrorDefine;
import umc.spring.repositroy.ReviewRepositroy;
import umc.spring.repositroy.StoreRepository;
import umc.spring.repositroy.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepositroy reviewRepositroy;

    public Map<String, Object> userReviewList(Integer page, Integer size, UserIdDto userIdDto) {
        User user = userRepository.findById(userIdDto.getUserId())
                .orElseThrow(() -> new ApiException(ErrorDefine.INVALID_ARGUMENT));

        Pageable pageable = PageRequest.of(page -1, size);
        Page<Review> selectReview = reviewRepositroy.findByUserId(user, pageable);

        PageInfo pageInfo = PageInfo.builder()
                .currentPage(selectReview.getNumber() + 1)
                .totalPages(selectReview.getTotalPages())
                .pageSize(selectReview.getSize())
                .currentItems(selectReview.getNumberOfElements())
                .totalItems(selectReview.getTotalElements())
                .build();

        Map<String, Object> result = new HashMap<>();

        result.put("selectReview",selectReview.getContent().stream()
                .map(reviews-> UserReviewDto.builder()
                        .storeName(reviews.getStoreId().getStoreName())
                        .reviewContent(reviews.getReviewContent())
                        .reviewStar(reviews.getReviewStar())
                        .build())
                .collect(Collectors.toList()));

        result.put("pageInfo",pageInfo);

        return result;
    }
}
