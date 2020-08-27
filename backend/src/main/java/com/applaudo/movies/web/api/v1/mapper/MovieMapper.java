package com.applaudo.movies.web.api.v1.mapper;

import com.applaudo.movies.domain.Movie;
import com.applaudo.movies.web.api.v1.model.MovieDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MovieMapper {

//    MovieMapper INSTANCE = Mappers.getMapper(MovieMapper.class);

    MovieDTO movieToMovieDTO(Movie movie);

    Movie movieDtoToMovie(MovieDTO movieDTO);

}
