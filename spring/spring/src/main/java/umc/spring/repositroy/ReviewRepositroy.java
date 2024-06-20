package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.Review;

public interface ReviewRepositroy extends JpaRepository<Review, Long> {

}
