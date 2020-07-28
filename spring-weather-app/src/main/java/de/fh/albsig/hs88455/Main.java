/**
 * Contains class for running the application.
 */

package de.fh.albsig.hs88455;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main class.
 *
 * @author svenb
 *
 */
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

  @Override
  protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
    return builder.sources(Main.class);
  }

  /**
   * Main method running the app.
   *
   * @param args main-method arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(Main.class, args);
  }

}
