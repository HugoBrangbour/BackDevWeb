package com.bnm.repository;

import com.bnm.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * The interface Vote repository.
 */
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    /**
     * Find by sondage optional.
     *
     * @param sondageId the sondage
     * @return the optional
     */
    @Query("SELECT v FROM Vote v WHERE v.sondage.id = :sondageId")
    List<Vote> findBySondage(Integer sondageId);

    /**
     * Find by id.
     * @param integer the id
     * @return the Optional vote
     */
    @Override
    Optional<Vote> findById(Integer integer);

}