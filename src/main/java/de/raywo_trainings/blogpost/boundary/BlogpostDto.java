package de.raywo_trainings.blogpost.boundary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BlogpostDto {

  private String id;
  private String title;
  private String text;
  private String author;
  private String createdAt;
  private String updatedAt;

}
