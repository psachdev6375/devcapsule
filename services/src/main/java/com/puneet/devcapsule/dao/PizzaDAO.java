/**
 * @author PSachdev
 * @version Revision: 1.0 $
 */
package com.puneet.devcapsule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.devcapsule.domain.Pizza;

/**
 * The Interface PizzaDAO.
 */
public interface PizzaDAO extends JpaRepository<Pizza, Long> {
  /**
   * Find by id.
   *
   * @param id
   *          the id
   * @return the Pizza
   */
  Pizza findById(Long id);
}
