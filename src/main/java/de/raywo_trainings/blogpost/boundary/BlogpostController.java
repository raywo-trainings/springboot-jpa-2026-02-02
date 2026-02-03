package de.raywo_trainings.blogpost.boundary;

import de.raywo_trainings.blogpost.control.BlogpostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/blogposts")
@RequiredArgsConstructor
public class BlogpostController {

  private final BlogpostService service;
  private final BlogpostMapper mapper;


  @GetMapping()
  public Collection<BlogpostDto> getBlogposts() {
    return service.getBlogposts()
        .stream()
        .map(mapper::map)
        .toList();
  }


  @GetMapping("/{id}")
  public BlogpostDto getBlogpost(@PathVariable String id) {
    return mapper.map(service.getById(id));
  }


  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public BlogpostDto createBlogpost(@RequestBody BlogpostDto dto) {
    var newBlogpost = service.createBlogpost(mapper.map(dto));

    return mapper.map(newBlogpost);
  }


  @PutMapping("/{id}")
  public BlogpostDto updateBlogpost(@PathVariable String id,
                                    @RequestBody BlogpostDto dto) {
    var updatedBlogpost = service.updateBlogpost(id, mapper.map(dto));

    return mapper.map(updatedBlogpost);
  }


  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBlogpost(@PathVariable String id) {
    service.deleteBlogpost(id);
  }

}
