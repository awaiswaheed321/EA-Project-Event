package edu.miu.cs544.awais.EventManagementService.domain.category;

import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.UpdateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/category")
@SecurityRequirement(name = "bearerAuth")
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

    @GetMapping("/{emId}/events")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<Event>> getEventsByCategoryId(@PathVariable Long emId) {
        return categoryService.getEventsByCategoryId(emId);
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
        return categoryService.deleteCategory(emId);
    }
}
