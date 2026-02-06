package de.raywo_trainings.blogpost_service.blogposts.control;

import de.raywo_trainings.blogpost_service.blogposts.entity.BlogpostEntity;
import org.jspecify.annotations.NonNull;
import org.mapstruct.*;

import java.time.Clock;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring")
public abstract class BlogpostMapper {

  abstract BlogpostRead map(BlogpostEntity entity);


  /**
   * Maps the provided {@link BlogpostWrite} data transfer object to a
   * {@link BlogpostEntity}.
   * Certain fields, including {@code id}, {@code createdAt}, and
   * {@code updatedAt}, are ignored during the mapping process.
   *
   * @param dto   The {@code BlogpostWrite} object containing input data to be
   *              mapped to an entity.
   * @param clock The {@code Clock} context used for any time-related operations
   *              during the mapping process.
   * @return A {@code BlogpostEntity} constructed using the mapped fields from
   *         the provided input.
   */
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public abstract BlogpostEntity map(@NonNull BlogpostWrite dto,
                                     @Context Clock clock);


  /**
   * Maps the provided {@link BlogpostWrite} data transfer object to a
   * {@link BlogpostEntity}.
   * This method also allows for setting specific fields such as `id` or
   * initializing timestamp fields.
   *
   * @param blogpostWrite The data transfer object containing blog post
   *                      input data.
   * @param existingId The ID to be assigned to the `id` field of the
   *                   resulting entity.
   * @param createdAt The predefined `createdAt` timestamp to assign to the
   *                  resulting entity. If null, the `createdAt` field will
   *                  remain uninitialized.
   * @param clock The clock context used to set the `updatedAt` timestamp or
   *              manage date/time processing.
   * @return A fully mapped {@link BlogpostEntity} constructed based on the
   *         input parameters and mapping logic.
   */
  @Mapping(target = "id", source = "existingId")
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  public abstract BlogpostEntity map(@NonNull BlogpostWrite blogpostWrite,
                                     String existingId,
                                     ZonedDateTime createdAt,
                                     @Context Clock clock);


  /**
   * Sets the `createdAt` and `updatedAt` timestamps for a new `BlogpostEntity`
   * during the mapping process.
   * Both timestamps are initialized to the current time based on the provided
   * `Clock` context.
   * <p>
   * **Note:** This method is only called when mapping `BlogpostWrite` to a new
   * `BlogpostEntity`, because MapStruct matches `@AfterMapping` methods by
   * their parameter lists (source, target, and `@Context` parameters).
   *
   * @param builder The builder for `BlogpostEntity` to configure the timestamps.
   * @param dto     The data transfer object containing blog post input data.
   * @param clock   The clock context used to determine the current time.
   */
  @AfterMapping
  protected void applyTimestampsForCreate(@MappingTarget BlogpostEntity.BlogpostEntityBuilder builder,
                                          @NonNull BlogpostWrite dto,
                                          @Context Clock clock) {
    var now = ZonedDateTime.now(clock);
    builder.createdAt(now);
    builder.updatedAt(now);
  }


  /**
   * Updates the `createdAt` and `updatedAt` timestamps for an existing `BlogpostEntity`
   * during the mapping process. The `createdAt` timestamp is set to the provided
   * value if available, otherwise it defaults to the current time. The `updatedAt`
   * timestamp is always set to the current time based on the provided `Clock` context.
   * <p>
   * **Note:** This method is only called when mapping `BlogpostWrite` to a new
   * `BlogpostEntity`, because MapStruct matches `@AfterMapping` methods by
   * their parameter lists (source, target, and `@Context` parameters).
   *
   * @param builder    The builder for `BlogpostEntity` to configure the timestamps.
   * @param dto        The data transfer object containing blog post input data.
   * @param existingId The ID of the existing blog post.
   * @param createdAt  The pre-existing `createdAt` timestamp of the blog post, or
   *                   null if it needs to be initialized.
   * @param clock      The clock context used to determine the current time.
   */
  @AfterMapping
  protected void applyTimestampsForUpdate(@MappingTarget BlogpostEntity.BlogpostEntityBuilder builder,
                                          @NonNull BlogpostWrite dto,
                                          String existingId,
                                          ZonedDateTime createdAt,
                                          @Context Clock clock) {
    var now = ZonedDateTime.now(clock);
    builder.createdAt(createdAt != null ? createdAt : now);
    builder.updatedAt(now);
  }

}
