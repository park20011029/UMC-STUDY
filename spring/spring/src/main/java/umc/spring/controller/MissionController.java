package umc.spring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import umc.spring.dto.request.UserIdDto;
import umc.spring.dto.response.ResponseDto;
import umc.spring.service.MissionService;

import java.util.Map;

@RestController
@RequestMapping("/mission")
@RequiredArgsConstructor
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/usermission")
    public ResponseDto<Map<String, Object>> userMission(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestBody UserIdDto userIdDto
            ) {
        return new ResponseDto<>(missionService.userMissionselect(page, size, userIdDto));
    }
}
