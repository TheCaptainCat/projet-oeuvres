package com.cgp.projetoeuvres.repository;

import com.cgp.projetoeuvres.entity.WorkForSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkForSaleRepository extends JpaRepository<WorkForSale, Integer>{
}
