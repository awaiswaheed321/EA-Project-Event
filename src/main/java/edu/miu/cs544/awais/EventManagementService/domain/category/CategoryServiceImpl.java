package edu.miu.cs544.awais.EventManagementService.domain.category;

import edu.miu.cs544.awais.EventManagementService.domain.category.domain.Category;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.CreateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.category.dto.UpdateCategoryDTO;
import edu.miu.cs544.awais.EventManagementService.domain.event.EventService;
import edu.miu.cs544.awais.EventManagementService.domain.event.domain.Event;
import edu.miu.cs544.awais.EventManagementService.domain.exception.custom.EntityNotFoundException;
import edu.miu.cs544.awais.EventManagementService.shared.EventSpecification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final EventService eventService;

    public CategoryServiceImpl(CategoryRepository categoryRepository, EventService eventService) {
        this.categoryRepository = categoryRepository;
        this.eventService = eventService;
    }

    @Override
    public ResponseEntity<Category> createCategory(CreateCategoryDTO request) {
        checkIfCategoryExistsByName(request.getName());
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.CREATED);
    }

    private void checkIfCategoryExistsByName(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new IllegalArgumentException("Category already exists: " + categoryName);
        }
    }

    @Override
    public ResponseEntity<Category> getCategoryById(Long emId) {
        return new ResponseEntity<>(findCategoryById(emId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Category> updateCategory(Long emId, UpdateCategoryDTO request) {
        Category category = findCategoryById(emId);
        checkIfCategoryExistsByName(request.getName());
        updateCategoryData(category, request);
        return new ResponseEntity<>(categoryRepository.save(category), HttpStatus.OK);
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
    public ResponseEntity<Void> deleteCategory(Long emId) {
        Category category = findCategoryById(emId);
        checkCategoryUsage(category);
        categoryRepository.delete(category);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<Event>> getEventsByCategoryId(Long emId) {
        Category category = findCategoryById(emId);
        List<Event> events = eventService.searchEvent(EventSpecification.categoryPredicate(category.getId()));
        return ResponseEntity.ok(events);
    }

    private void checkCategoryUsage(Category category) {
        if (eventService.getEventCountByCategory(category) != 0) {
            throw new IllegalArgumentException("Category already in use: " + category.getName());
        }
    }

    public Category findCategoryById(Long emId) {
        return categoryRepository.findById(emId).orElseThrow(() -> new EntityNotFoundException("Category not found: "
                + emId));
    }
}
