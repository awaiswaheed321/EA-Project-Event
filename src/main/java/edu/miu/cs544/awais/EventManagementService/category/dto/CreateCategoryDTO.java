package edu.miu.cs544.awais.EventManagementService.category.dto;

import jakarta.validation.constraints.NotEmpty;

public class CreateCategoryDTO {
    @NotEmpty(message = "Category Name must not be Empty")
    private String name;
    @NotEmpty(message = "Category Description must not be Empty")
    private String description;

    public CreateCategoryDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
