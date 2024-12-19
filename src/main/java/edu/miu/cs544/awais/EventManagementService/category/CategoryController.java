package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.UpdateCategoryDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategoryDTO request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("/{emId}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long emId) {
        return categoryService.getCategoryById(emId);
    }

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Category>> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Category> updateCategory(@PathVariable Long emId, @RequestBody UpdateCategoryDTO request) {
        return categoryService.updateCategory(emId, request);
    }

    @DeleteMapping("/{emId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long emId) {
        categoryService.deleteCategory(emId);
        return ResponseEntity.noContent().build();
    }
}
