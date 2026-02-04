package de.raywo_trainings.blogpost_service.shared.boundary;

import lombok.Builder;

@Builder
public record ConstraintViolationDTO(
    String field,
    String constraint,
    String message
) {
}
