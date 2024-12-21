package edu.miu.cs544.awais.EventManagementService.domain.category;

import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    boolean existsByName(String name);
}
