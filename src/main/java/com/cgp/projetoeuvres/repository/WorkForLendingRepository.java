package com.cgp.projetoeuvres.repository;

import com.cgp.projetoeuvres.entity.WorkForLending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkForLendingRepository extends JpaRepository<WorkForLending, Integer> {
}
