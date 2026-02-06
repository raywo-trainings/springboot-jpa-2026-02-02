package de.raywo_trainings.blogpost_service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cors.allow")
@Data
public class CorsConfigurationData {

  private String[] origins = { "*" };
  private boolean credentials = false;
  private String[] headers = {};

}
