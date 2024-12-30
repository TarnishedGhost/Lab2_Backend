package tarnishedghost.repo;

import tarnishedghost.structure.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    List<CategoryEntity> findByIsPublicTrue();
    List<CategoryEntity> findByUserId(UUID userId);
}