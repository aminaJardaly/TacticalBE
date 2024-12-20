package com.Tactical.TacticalBE.dto;


import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;

public class ItemRequestDto {
    @NotNull
    @Size(min = 1, message = "Name must not be empty.")
    private String name;

    private String description;

    // Getters and setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}

