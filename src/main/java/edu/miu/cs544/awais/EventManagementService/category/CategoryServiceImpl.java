package edu.miu.cs544.awais.EventManagementService.category;

import edu.miu.cs544.awais.EventManagementService.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.category.dto.CategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.category.dto.UpdateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public ResponseEntity<CategoryDTO> createCategory(CreateCategoryDTO request) {
        checkIfCategoryExistsByName(request.getName());
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        Category savedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(new CategoryDTO(savedCategory), HttpStatus.CREATED);
    }

    private void checkIfCategoryExistsByName(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Category already exists: " + categoryName);
        }
    }

    @Override
    public ResponseEntity<CategoryDTO> getCategoryById(Long emId) {
        Category category = findCategoryById(emId);
        return new ResponseEntity<>(new CategoryDTO(category), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = categories.stream().map(CategoryDTO::new).toList();
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryDTO> updateCategory(Long emId, UpdateCategoryDTO request) {
        Category category = findCategoryById(emId);
        checkIfCategoryExistsByName(request.getName());
        updateCategoryData(category, request);
        Category updatedCategory = categoryRepository.save(category);
        return new ResponseEntity<>(new CategoryDTO(updatedCategory), HttpStatus.OK);
    }

    private void updateCategoryData(Category category, UpdateCategoryDTO request) {
        if (request.getName() != null) {
            category.setName(request.getName());
        }
        if (request.getDescription() != null) {
            category.setDescription(request.getDescription());
        }
    }

    @Override
    public void deleteCategory(Long emId) {
        Category category = findCategoryById(emId);
        categoryRepository.delete(category);
    }

    public Category findCategoryById(Long emId) {
        return categoryRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Category not found: "
                + emId));
    }
}
