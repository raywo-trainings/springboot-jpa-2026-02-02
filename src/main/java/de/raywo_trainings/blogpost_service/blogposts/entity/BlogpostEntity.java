package de.raywo_trainings.blogpost_service.blogposts.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogpostEntity {

  @Id
  @GeneratedValue(generator = "UUID")
  private String id;

  @NotNull
  @Size(min = 1, max = 200)
  private String title;

  @NotNull
  @Size(min = 1)
  private String text;

  @NotNull
  @Size(min = 1, max = 30)
  private String author;

  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

}
