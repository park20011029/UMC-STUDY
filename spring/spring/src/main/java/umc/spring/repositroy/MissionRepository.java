package umc.spring.repositroy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.entity.*;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findByStoreId(Store storeId, Pageable pageable);

}
