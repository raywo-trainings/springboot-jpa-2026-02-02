package de.raywo_trainings.blogpost_service.blogposts.boundary;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record BlogpostWriteDto(
    @NotNull
    @Size(min = 5, max = 200)
    String title,

    @NotNull
    @Size(min = 10)
    String text,

    @NotNull
    @Size(min = 3, max = 30)
    String author
) {
}
