package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
