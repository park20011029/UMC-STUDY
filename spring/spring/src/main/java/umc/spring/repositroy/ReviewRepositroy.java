package umc.spring.repositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.MissionStatus;
import umc.spring.entity.Review;
import umc.spring.entity.User;
import umc.spring.entity.UserMission;

public interface ReviewRepositroy extends JpaRepository<Review, Long> {
    Page<Review> findByUserId(User userId, Pageable pageable);

}
