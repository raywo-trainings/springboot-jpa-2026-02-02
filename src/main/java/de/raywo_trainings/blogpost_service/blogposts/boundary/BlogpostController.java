package de.raywo_trainings.blogpost_service.blogposts.boundary;

import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/blogposts")
@RequiredArgsConstructor
public class BlogpostController {

  private final BlogpostService service;
  private final BlogpostDtoMapper mapper;


  @GetMapping()
  public Collection<BlogpostReadDto> getBlogposts() {
    return service.getBlogposts()
        .stream()
        .map(mapper::map)
        .toList();
  }


  @GetMapping("/{id}")
  public BlogpostReadDto getBlogpost(@PathVariable String id) {
    return mapper.map(service.getById(id));
  }


  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public BlogpostReadDto createBlogpost(@RequestBody @Valid BlogpostWriteDto dto) {
    var newBlogpost = service.createBlogpost(mapper.map(dto));

    return mapper.map(newBlogpost);
  }


  @PutMapping("/{id}")
  public BlogpostReadDto updateBlogpost(@PathVariable String id,
                                        @RequestBody @Valid BlogpostWriteDto dto) {
    var updatedBlogpost = service.updateBlogpost(id, mapper.map(dto));

    return mapper.map(updatedBlogpost);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBlogpost(@PathVariable String id) {
    service.deleteBlogpost(id);
  }

}
