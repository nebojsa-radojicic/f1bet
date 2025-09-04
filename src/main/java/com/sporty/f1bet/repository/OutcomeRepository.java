package com.sporty.f1bet.repository;

import com.sporty.f1bet.entity.OutcomeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface OutcomeRepository extends JpaRepository<OutcomeEntity, Integer> {

    Optional<OutcomeEntity> findByProviderAndEvent(String provider, Integer event);

    Collection<OutcomeEntity> findByProcessed(String processed);

}