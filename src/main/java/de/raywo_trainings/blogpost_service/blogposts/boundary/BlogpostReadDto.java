package de.raywo_trainings.blogpost_service.blogposts.boundary;

public record BlogpostReadDto(
    String id,
    String title,
    String text,
    String author,
    String createdAt,
    String updatedAt
) {
}
