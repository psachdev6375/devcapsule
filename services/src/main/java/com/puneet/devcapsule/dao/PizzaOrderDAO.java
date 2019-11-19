/**
 * @author PSachdev
 * @version Revision: 1.0 $
 */
package com.puneet.devcapsule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.devcapsule.domain.PizzaOrder;

/**
 * The Interface PizzaDAO.
 */
public interface PizzaOrderDAO extends JpaRepository<PizzaOrder, Long> {
  /**
   * Find by id.
   *
   * @param id
   *          the id
   * @return the Pizza Order
   */
  PizzaOrder findById(Long id);
}
