package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.RentalAndPurchases;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalAndPurchasesRepository extends JpaRepository<RentalAndPurchases, Long> {
}
