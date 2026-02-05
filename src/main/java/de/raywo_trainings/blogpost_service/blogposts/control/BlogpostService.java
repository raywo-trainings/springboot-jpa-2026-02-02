package de.raywo_trainings.blogpost_service.blogposts.control;

import de.raywo_trainings.blogpost_service.blogposts.entity.BlogpostRepository;
import de.raywo_trainings.blogpost_service.shared.control.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class BlogpostService {

  private final BlogpostRepository repo;
  private final BlogpostMapper mapper;


  @NonNull
  public Collection<BlogpostRead> getBlogposts() {
    return repo.findAll()
        .stream()
        .map(mapper::map)
        .toList();
  }


  public BlogpostRead getById(@NonNull String id) {
    return repo.findById(id)
        .map(mapper::map)
        .orElseThrow(() -> new NotFoundException("Blogpost", id));
  }


  public BlogpostRead createBlogpost(@NonNull BlogpostWrite blogpost) {
    var newBlogpost = mapper.map(blogpost);

    return mapper.map(repo.save(newBlogpost));
  }


  public BlogpostRead updateBlogpost(@NonNull String id,
                                     @NonNull BlogpostWrite blogpost) {
    assertBlogpostExists(id);

    var oldBlogpost = getById(id);
    var createdAt = oldBlogpost.getCreatedAt();

    var newBlogpost = mapper.map(blogpost, oldBlogpost.getId(), createdAt);

    return mapper.map(repo.save(newBlogpost));
  }


  public void deleteBlogpost(@NonNull String id) {
    assertBlogpostExists(id);
    repo.deleteById(id);
  }


  private void assertBlogpostExists(@NonNull String id) {
    if (!repo.existsById(id)) {
      throw new NotFoundException("Blogpost", id);
    }
  }

}
