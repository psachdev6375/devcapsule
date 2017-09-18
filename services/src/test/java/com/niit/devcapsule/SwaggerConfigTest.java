package com.niit.devcapsule;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import springfox.documentation.spring.web.plugins.Docket;

/**
 * The class <code>SwaggerConfigTest</code> contains tests for the class
 * <code>{@link SwaggerConfig}</code>.
 *
 * @author PSachdev
 * @version $Revision: 1.0 $
 */
public class SwaggerConfigTest {

  SwaggerConfig fixture;
  
  @Test
  public void testDocketCreation() {
    fixture = new SwaggerConfig();
    Docket docket = fixture.api();
    assertNotNull(docket);
  }

}
