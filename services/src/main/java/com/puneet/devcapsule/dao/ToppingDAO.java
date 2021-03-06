/**
 * @author PSachdev
 * @version Revision: 1.0 $
 */
package com.puneet.devcapsule.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.puneet.devcapsule.domain.Topping;

/**
 * The Interface ToppingDAO.
 */
public interface ToppingDAO extends JpaRepository<Topping, Long> {

  /**
   * Find by id.
   *
   * @param id
   *          the id
   * @return the topping
   */
  Topping findById(Long id);

  /**
   * Find by name.
   *
   * @param name
   *          the name
   * @return the topping
   */
  Topping findByName(String name);
}
