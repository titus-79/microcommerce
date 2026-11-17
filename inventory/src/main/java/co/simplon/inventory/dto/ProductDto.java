package co.simplon.inventory.dto;

import java.util.UUID;

public record ProductDto(UUID id, String name, String category) {}
