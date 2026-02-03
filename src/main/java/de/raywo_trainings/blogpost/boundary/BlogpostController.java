package de.raywo_trainings.blogpost.boundary;

import de.raywo_trainings.blogpost.control.BlogpostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class BlogpostController {

  private final BlogpostService service;
  private final BlogpostMapper mapper;


  @GetMapping("/blogposts")
  public Collection<BlogpostDto> getBlogposts() {
    return service.getBlogposts()
        .stream()
        .map(mapper::map)
        .toList();
  }


  @GetMapping("/blogposts/{id}")
  public BlogpostDto getBlogpost(@PathVariable String id) {
    return mapper.map(service.getById(id));
  }

}
