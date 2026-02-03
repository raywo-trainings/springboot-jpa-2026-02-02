package de.raywo_trainings.blogpost;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class BlogpostController {

  private final Map<String, BlogpostDto> blogposts = new HashMap<>();


  BlogpostController() {
    initializeData();
  }


  public void initializeData() {
    final BlogpostDto blogpost1 = new BlogpostDto(
        UUID.randomUUID().toString(),
        "Blogpost 1",
        "Text 1",
        "Author 1",
        "2021-02-01",
        "2021-02-01");
    blogposts.put(blogpost1.getId(), blogpost1);

    final BlogpostDto blogpost2 = new BlogpostDto(
        UUID.randomUUID().toString(),
        "Blogpost 2",
        "Text 2",
        "Author 2",
        "2021-02-02",
        "2021-02-02");
    blogposts.put(blogpost2.getId(), blogpost2);
  }


  @GetMapping("/blogposts")
  public Collection<BlogpostDto> getBlogposts() {
    return blogposts.values();
  }


  @GetMapping("/blogposts/{id}")
  public BlogpostDto getBlogpost(@PathVariable String id) {
    return blogposts.get(id);
  }

}
