package com.applaudo.movies.repositories;

import com.applaudo.movies.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query(value = "SELECT count(*) FROM MOVIE_LIKES where id_movie = 1 and id_user = 1", nativeQuery = true)
    Integer hasLike(@Param("idMovie") Long idMovie, @Param("idUser") Long idUser);

    @Modifying
    @Query(value = "INSERT INTO movie_likes(id_movie, id_user) VALUES (:idMovie, :idUser)", nativeQuery = true)
    void likeAMovie(@Param("idMovie") Long idMovie, @Param("idUser") Long idUser);

    @Modifying
    @Query(value = "DELETE FROM movie_likes WHERE id_movie = :idMovie and id_user = :idUser", nativeQuery = true)
    void dislikeAMovie(@Param("idMovie") Long idMovie, @Param("idUser") Long idUser);

    @Query(value = "SELECT * FROM movie where title = :movieName", nativeQuery = true)
    Optional<Movie> findByName(@Param("movieName") String movieName);
}
