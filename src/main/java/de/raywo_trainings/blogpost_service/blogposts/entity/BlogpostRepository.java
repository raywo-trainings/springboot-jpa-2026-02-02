package de.raywo_trainings.blogpost_service.blogposts.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface BlogpostRepository extends JpaRepository<BlogpostEntity, String> {

  List<BlogpostEntity> findByAuthor(String author);

  List<BlogpostEntity> findByCreatedAtBetween(
      ZonedDateTime start,
      ZonedDateTime end
  );

  List<BlogpostEntity> findByAuthorAndCreatedAtBetween(
      String author,
      ZonedDateTime start,
      ZonedDateTime end);

}
