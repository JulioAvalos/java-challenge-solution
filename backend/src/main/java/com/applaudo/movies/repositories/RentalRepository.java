package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
