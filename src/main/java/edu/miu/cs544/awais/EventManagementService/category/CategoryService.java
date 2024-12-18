package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.category.dto.CategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.UpdateCategoryDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    ResponseEntity<CategoryDTO> createCategory(CreateCategoryDTO request);

    ResponseEntity<CategoryDTO> getCategoryById(Long emId);

    ResponseEntity<List<CategoryDTO>> getAllCategories();

    ResponseEntity<CategoryDTO> updateCategory(Long emId, UpdateCategoryDTO request);

    void deleteCategory(Long emId);

    Category findCategoryById(Long emId);
}
