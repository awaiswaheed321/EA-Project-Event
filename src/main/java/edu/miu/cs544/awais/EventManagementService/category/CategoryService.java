package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.UpdateCategoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<Category> createCategory(CreateCategoryDTO request);

    ResponseEntity<Category> getCategoryById(Long emId);

    ResponseEntity<List<Category>> getAllCategories();

    ResponseEntity<Category> updateCategory(Long emId, UpdateCategoryDTO request);

    void deleteCategory(Long emId);

    Category findCategoryById(Long emId);
}
