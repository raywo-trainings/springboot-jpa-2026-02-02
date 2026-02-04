package de.raywo_trainings.blogpost_service.blogposts.control;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blogpost {

  private String id;
  private String title;
  private String text;
  private String author;
  private ZonedDateTime createdAt;
  private ZonedDateTime updatedAt;

}
