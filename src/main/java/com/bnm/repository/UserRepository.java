package com.bnm.repository;

import com.bnm.entity.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * The interface User repository.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    /**
     * Find by nom user.
     *
     * @param nom the nom
     * @return the user
     */
    User findByNom(String nom);

    /**
     * Find by id.
     * @param integer the id
     * @return the Optional user
     */
    @Override
    Optional<User> findById(Integer integer);
}
