package de.raywo_trainings.blogpost_service.blogposts.boundary;

import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostRead;
import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostWrite;
import org.mapstruct.Mapper;

import java.util.Collection;

@Mapper(componentModel = "spring")
public interface BlogpostDtoMapper {

  BlogpostReadDto map(BlogpostRead blogpost);

  BlogpostWrite map(BlogpostWriteDto dto);

  Collection<BlogpostReadDto> map(Collection<BlogpostRead> blogposts);

}
