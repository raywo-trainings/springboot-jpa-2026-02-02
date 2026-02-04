package de.raywo_trainings.blogpost_service.shared.control;

import org.jspecify.annotations.NonNull;

public class NotFoundException extends RuntimeException {

  private final String message;


  public NotFoundException(@NonNull String resourceName,
                           @NonNull String id) {
    this.message = String.format("%s with id '%s' not found.", resourceName, id);
  }


  @Override
  public String getMessage() {
    return message;
  }

}
