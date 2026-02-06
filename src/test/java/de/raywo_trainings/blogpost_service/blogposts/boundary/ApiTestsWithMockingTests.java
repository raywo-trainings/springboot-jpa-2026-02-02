package de.raywo_trainings.blogpost_service.blogposts.boundary;

import de.raywo_trainings.blogpost_service.blogposts.control.BlogpostService;
import de.raywo_trainings.blogpost_service.shared.control.NotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ApiTestsWithMockingTests {

  @Autowired
  MockMvc mvc;

  @MockitoBean
  BlogpostService service;


  @Test
  public void shouldReturn404OnNonExistingBlogpost() throws Exception {
    var id = "non-existing-id";

    Mockito
        .when(service.getById(id))
        .thenThrow(NotFoundException.class);

    mvc
        .perform(get("/blogposts/" + id))
        .andExpect(status().isNotFound());
  }

}
