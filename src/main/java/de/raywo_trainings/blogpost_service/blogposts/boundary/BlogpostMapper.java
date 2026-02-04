package de.raywo_trainings.blogpost_service.blogposts.boundary;

import de.raywo_trainings.blogpost_service.blogposts.control.Blogpost;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

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


  public Blogpost map(BlogpostDto dto) {
    return new Blogpost(
        dto.getId(),
        dto.getTitle(),
        dto.getText(),
        dto.getAuthor(),
        ZonedDateTime.parse(dto.getCreatedAt()),
        ZonedDateTime.parse(dto.getUpdatedAt())
    );
  }

}
