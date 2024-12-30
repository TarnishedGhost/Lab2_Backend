package tarnishedghost.repo;

import tarnishedghost.structure.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface RecordRepository extends JpaRepository<RecordEntity, UUID> {
    List<RecordEntity> findAllByUser_IdAndCategory_Id(UUID userId, UUID categoryId);
}
