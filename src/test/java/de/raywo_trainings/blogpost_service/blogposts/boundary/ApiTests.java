package de.raywo_trainings.blogpost_service.blogposts.boundary;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiTests {

  @Autowired
  MockMvc mvc;


  @Test
  public void shouldReturnBlogposts() throws Exception {
    mvc
        .perform(
            get("/blogposts")
                .accept(MediaType.APPLICATION_JSON)
        )
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }


  @Test
  @DisplayName("Should create blogpost and return created blogpost")
  public void shouldCreateBlogpostAndReturnCreatedBlogpost() throws Exception {
    String blogpostJson = """
        {
          "title": "My fancy title",
          "text": "This is the content of the new blogpost.",
          "author": "New Author"
        }
        """;

    var location = mvc
        .perform(
            post("/blogposts")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(blogpostJson)
        )
        .andExpect(status().isCreated())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("My fancy title"))
        .andExpect(jsonPath("$.text").value("This is the content of the new blogpost."))
        .andExpect(jsonPath("$.author").value("New Author"))
        .andExpect(jsonPath("$.id").isNotEmpty())
        .andExpect(header().exists("Location"))
        .andReturn()
        .getResponse()
        .getHeader("Location");

    mvc
        .perform(
            get(location)
        )
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.title").value("My fancy title"))
        .andExpect(jsonPath("$.text").value("This is the content of the new blogpost."))
        .andExpect(jsonPath("$.author").value("New Author"));
  }

}
