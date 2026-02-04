package de.raywo_trainings.blogpost_service.blogposts.control;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogpostWrite {

  @NotNull
  @NotBlank
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  @NotBlank
  @Size(min = 10)
  private String text;

  @NotNull
  @NotBlank
  @Size(min = 1, max = 50)
  private String author;

}
