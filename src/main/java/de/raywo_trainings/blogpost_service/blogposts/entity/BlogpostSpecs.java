package de.raywo_trainings.blogpost_service.blogposts.entity;

import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class BlogpostSpecs {

  private BlogpostSpecs() {
  }


  public static Specification<BlogpostEntity> hasAuthor(String author) {
    return (root, query, cb) -> {
      if (author == null || author.isBlank()) return null;

      String escaped = author
          .trim()
          .replace("\\", "\\\\")
          .replace("%", "\\%")
          .replace("_", "\\_")
          .toLowerCase();

      String pattern = "%" + escaped + "%";

      return cb.like(cb.lower(root.get("author")), pattern, '\\');
    };
  }


  /**
   * Filtert alle Blogposts, deren createdAt innerhalb des angegebenen Kalendertags liegt
   * (interpretiert in der angegebenen Zone).
   */
  public static Specification<BlogpostEntity> createdAtOnDay(LocalDate day, ZoneId zoneId) {
    return (root, query, cb) -> {
      if (day == null) return null;

      ZoneId zone = (zoneId != null) ? zoneId : ZoneId.systemDefault();

      ZonedDateTime start = day.atStartOfDay(zone);
      ZonedDateTime end = start.plusDays(1);

      return cb.and(
          cb.greaterThanOrEqualTo(root.get("createdAt"), start),
          cb.lessThan(root.get("createdAt"), end)
      );
    };
  }

}
