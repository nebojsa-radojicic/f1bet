package com.sporty.f1bet.repository;

import com.sporty.f1bet.entity.BetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface BetRepository extends JpaRepository<BetEntity, Integer> {

    Collection<BetEntity> findByProviderAndEvent(String provider, Integer event);

}