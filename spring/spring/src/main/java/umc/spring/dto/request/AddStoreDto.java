package umc.spring.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddStoreDto {
    private String regionName;
    private String storeName;
    private String address;
}
