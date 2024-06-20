package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.Region;

import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findByRegionName(String regionName);
}
