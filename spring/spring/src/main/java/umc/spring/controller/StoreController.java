package umc.spring.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.dto.request.AddReviewDto;
import umc.spring.dto.request.AddStoreDto;
import umc.spring.dto.response.ResponseDto;
import umc.spring.service.StoreService;

@RestController
@RequestMapping("/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @PostMapping("/add-store")
    public ResponseDto<Boolean> addStore(
            @Valid @RequestBody AddStoreDto addStoreDto
    ) {
        return new ResponseDto<>(storeService.addStore(addStoreDto));
    }

    @PostMapping("/add-review")
    public ResponseDto<Boolean> addReview(
            @Valid @RequestBody AddReviewDto addReviewDto
            ){
        return new ResponseDto<>(storeService.addReview(addReviewDto));
    }

    @PostMapping("/mission/user")
    public ResponseDto<Boolean> addUserMission(
            @RequestParam(name = "missionId") Long missionId,
            @RequestParam(name = "userId") Long userId
    ) {
        return new ResponseDto<>(storeService.addUserMission(missionId, userId));
    }

}
