package de.raywo_trainings.blogpost_service.blogposts.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogpostRepository extends JpaRepository<BlogpostEntity, String> {
}
