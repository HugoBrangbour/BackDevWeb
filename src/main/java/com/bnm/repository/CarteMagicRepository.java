package com.bnm.repository;

import com.bnm.entity.CarteMagic;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;
import java.util.Set;

public interface CarteMagicRepository extends PagingAndSortingRepository<CarteMagic, Integer> {
    Set<CarteMagic> findByToughnessIgnoreCase(String toughness);
    Set<CarteMagic> findByPowerIgnoreCase(String power);
    Set<CarteMagic> findByTextContainsIgnoreCase(String text);
    //A surveiller pour voir si ça fonctionne
    //Twist, ça fonctionne pas
    Set<CarteMagic> findByTypeContainingIgnoreCase(String type);
    Set<CarteMagic> findByColorsIgnoreCase(String colors);
    Set<CarteMagic> findByManaCostIgnoreCase(String manaCost);
    Set<CarteMagic> findByManaCostContainsIgnoreCase(String manaCost);
    Set<CarteMagic> findByConvertedManaCost(Integer convertedManaCost);
    Set<CarteMagic> findByNameIgnoreCase(String name);
    Set<CarteMagic> findByNameContainsIgnoreCase(String name);
    @Override
    Optional<CarteMagic> findById(Integer integer);

}