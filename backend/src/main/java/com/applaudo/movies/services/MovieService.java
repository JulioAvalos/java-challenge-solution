package com.applaudo.movies.services;

import com.applaudo.movies.util.FilterBy;
import com.applaudo.movies.web.api.v1.model.MovieDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieService extends BaseCrud<MovieDTO> {

    Page<MovieDTO> pageableMovieList(Pageable pageable, boolean sortByLikes, FilterBy filterByAvailability);

    MovieDTO patchMovie(Long id, MovieDTO movieDTO);

    boolean likeAMovie(Long idMovie, Long idUser);

    boolean dislikeAMovie(Long idMovie, Long idUser);

    MovieDTO getByName(String movieName);
}
