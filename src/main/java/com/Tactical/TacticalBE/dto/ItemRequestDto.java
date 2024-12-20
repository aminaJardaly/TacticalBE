package com.Tactical.TacticalBE.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ItemRequestDto {

    @NotNull(message = "Name is required.")
    @Size(min = 1, message = "Name must not be empty.")
    private String name;

    private String description;

    // Getters and Setters
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
