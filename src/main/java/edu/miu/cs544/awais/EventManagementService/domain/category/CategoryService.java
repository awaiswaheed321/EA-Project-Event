package edu.miu.cs544.awais.EventManagementService.domain.category;

import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.UpdateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<Category> createCategory(CreateCategoryDTO request);

    ResponseEntity<Category> getCategoryById(Long emId);

    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> updateCategory(Long emId, UpdateCategoryDTO request);

    ResponseEntity<Void> deleteCategory(Long emId);

    ResponseEntity<List<Event>> getEventsByCategoryId(Long emId);
}
