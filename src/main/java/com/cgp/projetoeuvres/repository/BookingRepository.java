package com.cgp.projetoeuvres.repository;


import com.cgp.projetoeuvres.entity.Adherent;
import com.cgp.projetoeuvres.entity.Booking;
import com.cgp.projetoeuvres.entity.BookingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, BookingKey> {
}
