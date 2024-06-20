package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long userId);
}
