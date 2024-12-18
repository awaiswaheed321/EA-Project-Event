package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.category.dto.CategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.UpdateCategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody @Valid CreateCategoryDTO request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{emId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long emId) {
        return categoryService.getCategoryById(emId);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{emId}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long emId, @RequestBody UpdateCategoryDTO request) {
        return categoryService.updateCategory(emId, request);
    }

    @DeleteMapping("/{emId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long emId) {
        categoryService.deleteCategory(emId);
        return ResponseEntity.noContent().build();
    }
}
