package de.raywo_trainings.blogpost_service.shared.boundary;

import de.raywo_trainings.blogpost_service.shared.control.NotFoundException;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public String handleNotFoundException(NotFoundException ex) {
    return ex.getMessage();
  }


  @Override
  protected @Nullable ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request
  ) {
    var httpStatus = HttpStatus.UNPROCESSABLE_CONTENT;
    ex.getBody().setStatus(httpStatus);

    ex.getBody().setProperty(
        "violations",
        ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(
                v -> ConstraintViolationDTO.builder()
                    .field(v.getField())
                    .constraint(v.getCode())
                    .message(v.getDefaultMessage())
                    .build()
            )
            .toList()
    );

    return super.handleMethodArgumentNotValid(ex, headers, httpStatus, request);
  }
}
