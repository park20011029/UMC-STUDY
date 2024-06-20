package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

}
