package com.cgp.projetoeuvres.repository;

import com.cgp.projetoeuvres.entity.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
}
