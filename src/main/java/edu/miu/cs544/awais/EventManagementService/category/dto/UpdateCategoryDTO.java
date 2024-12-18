package edu.miu.cs544.awais.EventManagementService.category.dto;

public class UpdateCategoryDTO {
    private String name;
    private String description;

    public UpdateCategoryDTO() {
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
