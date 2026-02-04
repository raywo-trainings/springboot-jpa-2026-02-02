package de.raywo_trainings.blogpost_service.blogposts.control;

import de.raywo_trainings.blogpost_service.shared.control.NotFoundException;
import org.jspecify.annotations.NonNull;
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
        "",
        "Blogpost 1",
        "Text 1",
        "Author 1",
        ZonedDateTime.parse("2021-02-02T07:00:00+01:00"),
        ZonedDateTime.parse("2021-02-02T12:27:00+01:00"));
    createBlogpost(blogpost1);

    final Blogpost blogpost2 = new Blogpost(
        "",
        "Blogpost 2",
        "Text 2",
        "Author 2",
        ZonedDateTime.parse("2021-02-02T07:00:00+01:00"),
        ZonedDateTime.parse("2021-02-02T12:27:00+01:00"));
    createBlogpost(blogpost2);
  }


  @NonNull
  public Collection<Blogpost> getBlogposts() {
    return blogposts.values();
  }


  public Blogpost getById(@NonNull String id) {
    assertBlogpostExists(id);

    return blogposts.get(id);
  }


  public Blogpost createBlogpost(@NonNull Blogpost blogpost) {
    var newId = UUID.randomUUID().toString();
    var now = ZonedDateTime.now();

    var newBlogpost = new Blogpost(
        newId,
        blogpost.getTitle(),
        blogpost.getText(),
        blogpost.getAuthor(),
        now,
        now
    );

    blogposts.put(newId, newBlogpost);

    return newBlogpost;
  }


  public Blogpost updateBlogpost(@NonNull String id,
                                 @NonNull Blogpost blogpost) {
    assertBlogpostExists(id);

    var oldBlogpost = blogposts.get(id);
    var createdAt = oldBlogpost.getCreatedAt();
    var updatedAt = ZonedDateTime.now();

    var newBlogpost = new Blogpost(
        id,
        blogpost.getTitle(),
        blogpost.getText(),
        blogpost.getAuthor(),
        createdAt,
        updatedAt
    );

    blogposts.put(id, newBlogpost);

    return newBlogpost;
  }


  public void deleteBlogpost(@NonNull String id) {
    assertBlogpostExists(id);
    blogposts.remove(id);
  }


  private void assertBlogpostExists(@NonNull String id) {
    if (!blogposts.containsKey(id)) {
      throw new NotFoundException("Blogpost", id);
    }
  }

}
