package de.raywo_trainings.blogpost_service.blogposts.entity;

import org.jspecify.annotations.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogpostRepository
    extends JpaRepository<BlogpostEntity, @NonNull String>,
            JpaSpecificationExecutor<BlogpostEntity> {

}
