package de.raywo_trainings.blogpost_service.blogposts.control;

import de.raywo_trainings.blogpost_service.blogposts.entity.BlogpostEntity;
import de.raywo_trainings.blogpost_service.blogposts.entity.BlogpostRepository;
import de.raywo_trainings.blogpost_service.shared.control.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogpostService {

  private final BlogpostRepository repo;
  private final BlogpostMapper mapper;
  private final Clock clock;


  @NonNull
  public Collection<BlogpostRead> getBlogposts(String author,
                                               LocalDate createdAt) {
    var zone = clock.getZone();
    List<BlogpostEntity> blogposts;

    if (author != null && createdAt != null) {
      ZonedDateTime start = createdAt.atStartOfDay(zone);
      ZonedDateTime end = start.plusDays(1);
      blogposts = repo.findByAuthorAndCreatedAtBetween(
          author,
          start,
          end
      );
    } else if (author != null) {
      blogposts = repo.findByAuthor(author);
    } else if (createdAt != null) {
      ZonedDateTime start = createdAt.atStartOfDay(zone);
      ZonedDateTime end = start.plusDays(1);
      blogposts = repo.findByCreatedAtBetween(start, end);
    } else {
      blogposts = repo.findAll();
    }

    return blogposts
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
    var newBlogpost = mapper.map(blogpost, clock);

    return mapper.map(repo.save(newBlogpost));
  }


  public BlogpostRead updateBlogpost(@NonNull String id,
                                     @NonNull BlogpostWrite blogpost) {
    assertBlogpostExists(id);

    var oldBlogpost = getById(id);
    var createdAt = oldBlogpost.getCreatedAt();

    var newBlogpost = mapper.map(blogpost, oldBlogpost.getId(), createdAt, clock);

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
