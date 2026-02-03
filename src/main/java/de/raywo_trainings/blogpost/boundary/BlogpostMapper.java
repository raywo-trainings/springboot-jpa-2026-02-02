package de.raywo_trainings.blogpost.boundary;

import de.raywo_trainings.blogpost.control.Blogpost;
import org.springframework.stereotype.Component;

@Component
public class BlogpostMapper {

  public BlogpostDto map(Blogpost blogpost) {
    return new BlogpostDto(
        blogpost.getId(),
        blogpost.getTitle(),
        blogpost.getText(),
        blogpost.getAuthor(),
        blogpost.getCreatedAt().toString(),
        blogpost.getUpdatedAt().toString());
  }

}
