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

  private final Map<String, BlogpostRead> blogposts = new HashMap<>();


  BlogpostService() {
    initializeData();
  }


  public void initializeData() {
    final BlogpostWrite blogpost1 = new BlogpostWrite(
        "Blogpost 1",
        "Text 1",
        "Author 1"
    );
    createBlogpost(blogpost1);

    final BlogpostWrite blogpost2 = new BlogpostWrite(
        "Blogpost 2",
        "Text 2",
        "Author 2"
    );
    createBlogpost(blogpost2);
  }


  @NonNull
  public Collection<BlogpostRead> getBlogposts() {
    return blogposts.values();
  }


  public BlogpostRead getById(@NonNull String id) {
    assertBlogpostExists(id);

    return blogposts.get(id);
  }


  public BlogpostRead createBlogpost(@NonNull BlogpostWrite blogpost) {
    var newId = UUID.randomUUID().toString();
    var now = ZonedDateTime.now();

    var newBlogpost = new BlogpostRead(
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


  public BlogpostRead updateBlogpost(@NonNull String id,
                                     @NonNull BlogpostWrite blogpost) {
    assertBlogpostExists(id);

    var oldBlogpost = blogposts.get(id);
    var createdAt = oldBlogpost.getCreatedAt();
    var updatedAt = ZonedDateTime.now();

    var newBlogpost = new BlogpostRead(
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
