package umc.spring.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import umc.spring.entity.Store;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Optional<Store> findByStoreName(String storeName);
}
