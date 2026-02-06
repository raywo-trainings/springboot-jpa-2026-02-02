package de.raywo_trainings.blogpost_service.blogposts.control;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Profile("dev")
@Slf4j
public class DataInitializer {

  private final BlogpostService service;


  @EventListener(ApplicationReadyEvent.class)
  public void initializeData() {
    if (!service.getBlogposts(null, null).isEmpty()) {
      log.info("Database is not empty. Skipping initialization.");
      return;
    }

    final BlogpostWrite blogpost1 = new BlogpostWrite(
        "Blogpost 1",
        "Text 1",
        "Author 1"
    );
    service.createBlogpost(blogpost1);

    final BlogpostWrite blogpost2 = new BlogpostWrite(
        "Blogpost 2",
        "Text 2",
        "Author 2"
    );
    service.createBlogpost(blogpost2);

    log.info("Found empty database. Inserted sample data.");
  }

}
