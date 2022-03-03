package com.bnm.repository;

import com.bnm.entity.Sondage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Sondage repository.
 */
public interface SondageRepository extends JpaRepository<Sondage, Integer> {
    /**
     * Find by ouvert list.
     *
     * @param ouvert the ouvert
     * @return the list
     */
    List<Sondage> findByOuvert(boolean ouvert);

    /**
     * Find by nom list.
     *
     * @param nom the nom
     * @return the list
     */
    List<Sondage> findByNom(String nom);

    /**
     * Find by id.
     * @param integer the id
     * @return the Optional sondage
     */
    @Override
    Optional<Sondage> findById(Integer integer);
}