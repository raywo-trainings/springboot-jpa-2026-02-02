package de.raywo_trainings.blogpost.control;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BlogpostService {

  private final Map<String, Blogpost> blogposts = new HashMap<>();


  BlogpostService() {
    initializeData();
  }


  public void initializeData() {
    final Blogpost blogpost1 = new Blogpost(
        UUID.randomUUID().toString(),
        "Blogpost 1",
        "Text 1",
        "Author 1",
        ZonedDateTime.parse("2021-02-01"),
        ZonedDateTime.parse("2021-02-01"));
    blogposts.put(blogpost1.getId(), blogpost1);

    final Blogpost blogpost2 = new Blogpost(
        UUID.randomUUID().toString(),
        "Blogpost 2",
        "Text 2",
        "Author 2",
        ZonedDateTime.parse("2021-02-02"),
        ZonedDateTime.parse("2021-02-02"));
    blogposts.put(blogpost2.getId(), blogpost2);
  }


  public Collection<Blogpost> getBlogposts() {
    return blogposts.values();
  }


  public Blogpost getById(String id) {
    return blogposts.get(id);
  }

}
