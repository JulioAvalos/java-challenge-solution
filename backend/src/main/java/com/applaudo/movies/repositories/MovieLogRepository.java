package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.MovieUpdateHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieLogRepository extends JpaRepository<MovieUpdateHistory, Long> {
}
