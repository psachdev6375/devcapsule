/**
 * @author PSachdev
 * @version Revision: 1.0 $
 */
package com.puneet.devcapsule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.puneet.devcapsule.domain.Pizza;
import com.puneet.devcapsule.service.PizzaService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * The Class PizzaController.
 */
@RestController
public class PizzaController {

  /** The pizza service. */
  @Autowired
  PizzaService pizzaService;

  /**
   * Gets the toppings.
   *
   * @return the toppings
   */
  @ApiOperation(value = "Get all the pizzas", response = Pizza.class, responseContainer = "List",
      produces = "application/json")
  @RequestMapping(value = "/pizzas", method = RequestMethod.GET)
  public Iterable<Pizza> getPizzas() {
    return pizzaService.findAll();
  }

  /**
   * Gets the toppings.
   *
   * @param pizza
   *          the pizza
   * @return the toppings
   */
  @ApiOperation(value = "Add a new pizza", notes = "Correct ID and Name combinations for Toppings need to be provided.",
      response = Pizza.class, produces = "application/json")
  @RequestMapping(value = "/pizzas", method = RequestMethod.POST, consumes = "application/json")
  public Pizza addPizza(@ApiParam(value = "Pizza to be added", required = true) @RequestBody Pizza pizza) {
    return pizzaService.addPizza(pizza);
  }

  /**
   * Save pizza.
   *
   * @param pizza
   *          the pizza
   * @param id
   *          the id
   * @return the pizza
   */
  @ApiOperation(value = "Save an existing pizza", response = Pizza.class, produces = "application/json")
  @RequestMapping(value = "/pizzas/{id}", method = RequestMethod.POST)
  public Pizza savePizza(@ApiParam(value = "Pizza to be saved", required = true) @RequestBody Pizza pizza,
      @ApiParam(value = "Id of the pizza to save", required = true) @PathVariable Long id) {
    Pizza isItthere = pizzaService.findById(id);
    if (isItthere == null) {
      throw new ResourceNotFoundException("Pizza with ID " + id + " not found");
    }
    return pizzaService.updatePizza(pizza);
  }
}
