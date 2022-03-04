package com.bnm.repository;

import com.bnm.entity.CarteMagic;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.Set;

public interface CarteMagicRepository extends JpaRepository<CarteMagic, Integer> {
    Set<CarteMagic> findByToughnessIgnoreCase(String toughness);
    Set<CarteMagic> findByPowerIgnoreCase(String power);
    Set<CarteMagic> findByTextContainsIgnoreCase(String text);
    //A surveiller pour voir si ça fonctionne
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