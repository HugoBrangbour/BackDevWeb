package com.bnm.repository;

import com.bnm.entity.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * The interface Commentaire repository.
 */
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {

    /**
     * Find by id sondage list.
     *
     * @param sondageId the id sondage
     * @return the list
     */
    @Query("SELECT c FROM Commentaire c WHERE c.sondage.id = :sondageId")
    List<Commentaire> findBySondage(@Param("sondageId") Integer sondageId);

    /**
     * Find by id.
     * @param integer the id
     * @return the Optional commentaire
     */
    @Override
    Optional<Commentaire> findById(Integer integer);
}