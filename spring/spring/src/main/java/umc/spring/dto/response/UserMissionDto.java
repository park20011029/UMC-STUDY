package umc.spring.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class UserMissionDto {
    private String storeName;
    private LocalDate missionDeadline;
    private Long missionReward;
}
