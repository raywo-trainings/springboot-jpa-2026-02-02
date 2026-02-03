package de.raywo_trainings.blogpost;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BlogpostController {

  private final List<BlogpostDto> blogposts = new ArrayList<>();


  BlogpostController() {
    initializeData();
  }


  public void initializeData() {
    blogposts.add(new BlogpostDto("Blogpost 1", "Text 1", "Author 1", "2021-02-01", "2021-02-01"));
    blogposts.add(new BlogpostDto("Blogpost 2", "Text 2", "Author 2", "2021-02-02", "2021-02-02"));
  }


  @GetMapping("/blogposts")
  public List<BlogpostDto> getBlogposts() {
    return blogposts;
  }

}
