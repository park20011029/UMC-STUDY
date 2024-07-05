package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.dto.request.UserIdDto;
import umc.spring.dto.response.ResponseDto;
import umc.spring.service.ReviewService;

import java.util.Map;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/userReview")
    public ResponseDto<Map<String, Object>> userReview(
        @RequestParam(name = "page", defaultValue = "1") Integer page,
        @RequestParam(name = "size", defaultValue = "10") Integer size,
        @RequestBody UserIdDto userIdDto
    ) {
        return new ResponseDto<>(reviewService.userReviewList(page, size, userIdDto));
    }
}
