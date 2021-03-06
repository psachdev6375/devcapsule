package com.puneet.devcapsule.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.easymock.IAnswer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;

import com.puneet.devcapsule.controller.PizzaOrderController;
import com.puneet.devcapsule.domain.Base;
import com.puneet.devcapsule.domain.Pizza;
import com.puneet.devcapsule.domain.PizzaOrder;
import com.puneet.devcapsule.domain.Topping;
import com.puneet.devcapsule.service.PizzaOrderService;

/**
 * The Class PizzaOrderControllerTest.
 */
public class PizzaOrderControllerTest {

  /** The pizza order servlce. */
  PizzaOrderService pizzaOrderServlce;

  /** The fixture. */
  PizzaOrderController fixture;

  /**
   * Sets the up.
   *
   * @throws Exception
   *           the exception
   */
  @Before
  public void setUp() throws Exception {
    fixture = new PizzaOrderController();
    pizzaOrderServlce = EasyMock.createNiceMock(PizzaOrderService.class);
    fixture.pizzaOrderService = pizzaOrderServlce;

    // getOrders
    Set<Pizza> pizzas = new HashSet<Pizza>();
    Set<Topping> toppings = new HashSet<Topping>();
    toppings.add(new Topping(1L, "Onion"));
    toppings.add(new Topping(2L, "Capsucum"));
    pizzas.add(new Pizza(1L, "Veggie", new BigDecimal(102.00), new Base(1L, "Pan"), toppings));
    List<PizzaOrder> orders = new ArrayList<PizzaOrder>();
    orders.add(new PizzaOrder(1L, pizzas, new BigDecimal(1020)));
    EasyMock.expect(pizzaOrderServlce.findAll()).andReturn(orders);

    // addOrder
    final Capture<PizzaOrder> pizzaOrderCapturedAdd = new Capture<PizzaOrder>();
    EasyMock.expect(pizzaOrderServlce.addOrder(EasyMock.capture(pizzaOrderCapturedAdd)))
        .andAnswer(new IAnswer<PizzaOrder>() {
          public PizzaOrder answer() throws Throwable {
            pizzaOrderCapturedAdd.getValue().setId(1L);
            return pizzaOrderCapturedAdd.getValue();
          }
        });

    // updatePizza
    final Capture<PizzaOrder> pizzaOrderCapturedUpdate = new Capture<PizzaOrder>();
    EasyMock.expect(pizzaOrderServlce.updateOrder(EasyMock.capture(pizzaOrderCapturedUpdate)))
        .andAnswer(new IAnswer<PizzaOrder>() {
          public PizzaOrder answer() throws Throwable {
            return pizzaOrderCapturedUpdate.getValue();
          }
        });

    EasyMock.expect(pizzaOrderServlce.findById(1L)).andReturn(new PizzaOrder(1L, null, null)).anyTimes();

    EasyMock.expect(pizzaOrderServlce.findById(2L)).andReturn(null).anyTimes();

    EasyMock.replay(pizzaOrderServlce);
  }

  /**
   * Test get orders.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testGetOrders() throws Exception {
    Iterable<PizzaOrder> result = fixture.getPizzaOrders();
    assertNotNull(result);
    assertEquals(result.iterator().next().getId().longValue(), 1L);
  }

  /**
   * Test add.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testAdd() throws Exception {
    Set<Pizza> pizzas = new HashSet<Pizza>();
    Pizza pizza = new Pizza();
    pizza.setId(1L);
    pizzas.add(pizza);
    PizzaOrder order = new PizzaOrder(pizzas, new BigDecimal(1020));
    order = fixture.addPizza(order);
    assertNotNull(order);
    assertEquals(order.getId().longValue(), 1L);
  }

  /**
   * Test save.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testSave() throws Exception {
    Set<Topping> toppings = new HashSet<Topping>();
    toppings.add(new Topping(1L, "Onion"));
    toppings.add(new Topping(2L, "Capsucum"));
    Pizza pizza = new Pizza(1L, "Veggie", new BigDecimal(102.00), new Base(1L, "Pan"), toppings);
    Set<Pizza> pizzas = new HashSet<Pizza>();
    pizzas.add(pizza);
    pizzas.add(pizza);
    pizzas.add(pizza);
    PizzaOrder order = new PizzaOrder(1L, pizzas, new BigDecimal(1020));
    order = fixture.savePizza(order, 1L);
    assertNotNull(order);
    assertEquals(order.getTotalPrice().longValue(), 1020);
  }

  /**
   * Test saveNotFound.
   *
   * @throws Exception
   *           the exception
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testSaveNotFound() throws Exception {
    Set<Topping> toppings = new HashSet<Topping>();
    toppings.add(new Topping(1L, "Onion"));
    toppings.add(new Topping(2L, "Capsucum"));
    Pizza pizza = new Pizza(1L, "Veggie", new BigDecimal(102.00), new Base(1L, "Pan"), toppings);
    Set<Pizza> pizzas = new HashSet<Pizza>();
    pizzas.add(pizza);
    pizzas.add(pizza);
    pizzas.add(pizza);
    PizzaOrder order = new PizzaOrder(2L, pizzas, new BigDecimal(1020));
    order = fixture.savePizza(order, 2L);
  }

  /**
   * Test delete.
   *
   * @throws Exception
   *           the exception
   */
  @Test
  public void testDelete() throws Exception {
    PizzaOrderController fixtureforDelete = new PizzaOrderController();
    PizzaOrderService pizzaOrderServlceForDelete = EasyMock.createNiceMock(PizzaOrderService.class);
    fixtureforDelete.pizzaOrderService = pizzaOrderServlceForDelete;
    final AtomicBoolean deleteCalled = new AtomicBoolean();

    // Add Order in Mock
    Set<Pizza> pizzas = new HashSet<Pizza>();
    Set<Topping> toppings = new HashSet<Topping>();
    toppings.add(new Topping(1L, "Onion"));
    toppings.add(new Topping(2L, "Capsucum"));
    pizzas.add(new Pizza(1L, "Veggie", new BigDecimal(102.00), new Base(1L, "Pan"), toppings));
    List<PizzaOrder> orders = new ArrayList<PizzaOrder>();
    orders.add(new PizzaOrder(1L, pizzas, new BigDecimal(1020)));
    EasyMock.expect(pizzaOrderServlceForDelete.findById(1L))
        .andReturn(new PizzaOrder(1L, pizzas, new BigDecimal(1020)));

    pizzaOrderServlceForDelete.deleteOrder(1L);
    EasyMock.expectLastCall().andAnswer(new IAnswer<Object>() {
      public Object answer() {
        deleteCalled.set(true);
        return null;
      }
    });
    
    EasyMock.replay(pizzaOrderServlceForDelete);
    fixtureforDelete.deletePizza(1L);
    EasyMock.verify(pizzaOrderServlceForDelete);
    assertEquals(Boolean.TRUE, deleteCalled.get());
  }

  /**
   * Test deleteNotFound.
   *
   * @throws Exception
   *           the exception
   */
  @Test(expected = ResourceNotFoundException.class)
  public void testDeleteNotFound() throws Exception {
    fixture.deletePizza(2L);
  }

  /**
   * Tear down.
   *
   * @throws Exception
   *           the exception
   */
  @After
  public void tearDown() throws Exception {
    // Add additional tear down code here
  }

  /**
   * The main method.
   *
   * @param args
   *          the arguments
   */
  public static void main(String[] args) {
    new org.junit.runner.JUnitCore().run(PizzaOrderControllerTest.class);
  }
}