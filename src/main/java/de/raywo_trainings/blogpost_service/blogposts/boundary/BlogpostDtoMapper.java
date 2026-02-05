package de.raywo_trainings.blogpost_service.blogposts.boundary;

import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostRead;
import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostWrite;
import org.springframework.stereotype.Component;

@Component
public class BlogpostDtoMapper {

  public BlogpostReadDto map(BlogpostRead blogpost) {
    return new BlogpostReadDto(
        blogpost.getId(),
        blogpost.getTitle(),
        blogpost.getText(),
        blogpost.getAuthor(),
        blogpost.getCreatedAt().toString(),
        blogpost.getUpdatedAt().toString());
  }


  public BlogpostWrite map(BlogpostWriteDto dto) {
    return new BlogpostWrite(
        dto.title(),
        dto.text(),
        dto.author()
    );
  }

}
