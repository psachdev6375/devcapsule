/**
 * @author PSachdev
 * @version Revision: 1.0 $
 */
package com.puneet.devcapsule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig.
 * Configures Swagger for documentation and testing services
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * Api.
   *
   * @return the docket
   */
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.puneet.devcapsule.controller")).build()
        .apiInfo(new ApiInfo("AWS Java DevCapsule", "AWS Developer Capsule API", "1.0", null,
            "Puneet Sachdev", "Open Source GNU GPLv3 License", null));
  }

}
