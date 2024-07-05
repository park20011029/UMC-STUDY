package umc.spring.repositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.spring.entity.MissionStatus;
import umc.spring.entity.User;
import umc.spring.entity.UserMission;

public interface UserMissionRepository extends JpaRepository<UserMission, Long> {

    Page<UserMission> findByUserIdAndMissionStatus(User userId, MissionStatus missionStatus, Pageable pageable);
}
