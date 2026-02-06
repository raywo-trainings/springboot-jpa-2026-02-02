package de.raywo_trainings.blogpost_service.blogposts.control;

import de.raywo_trainings.blogpost_service.blogposts.entity.BlogpostEntity;
import org.jspecify.annotations.NonNull;
import org.mapstruct.Mapper;

import java.time.ZonedDateTime;

@Mapper
public abstract class BlogpostMapper {

  abstract BlogpostRead map(BlogpostEntity entity);


  public BlogpostEntity map(@NonNull BlogpostWrite dto) {
    return map(dto, null, null);
  }


  public BlogpostEntity map(@NonNull BlogpostWrite dto,
                            String existingId,
                            ZonedDateTime createdAt) {
    var now = ZonedDateTime.now();
    var createdAtOrDefault = createdAt != null ? createdAt : now;

    return BlogpostEntity.builder()
        .id(existingId)
        .title(dto.getTitle())
        .text(dto.getText())
        .author(dto.getAuthor())
        .createdAt(createdAtOrDefault)
        .updatedAt(now)
        .build();
  }

}
