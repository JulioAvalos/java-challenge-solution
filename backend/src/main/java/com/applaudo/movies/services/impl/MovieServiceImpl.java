package com.applaudo.movies.services.impl;

import com.applaudo.movies.domain.Movie;
import com.applaudo.movies.domain.MovieUpdateHistory;
import com.applaudo.movies.exception.ResourceNotFoundException;
import com.applaudo.movies.repositories.MovieLogRepository;
import com.applaudo.movies.repositories.MovieRepository;
import com.applaudo.movies.services.MovieService;
import com.applaudo.movies.util.FilterBy;
import com.applaudo.movies.util.MovieSpecification;
import com.applaudo.movies.util.SearchCriteria;
import com.applaudo.movies.web.api.v1.controller.MovieController;
import com.applaudo.movies.web.api.v1.mapper.MovieMapper;
import com.applaudo.movies.web.api.v1.model.MovieDTO;
import com.applaudo.movies.web.api.v1.model.MoviePagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final MovieMapper movieMapper;
    private final MovieLogRepository logRepository;

    @Override
    public Page<MovieDTO> pageableMovieList(Pageable pageable, boolean sortByLikes, FilterBy filterByAvailability) {
        Specification<Movie> searchSpec = null;
        Page<Movie> moviePage = null;

        if (filterByAvailability.equals(FilterBy.AVAILABLE)) {
            MovieSpecification spec = new MovieSpecification(new SearchCriteria("availability", ":", true));
            moviePage = movieRepository.findAll(Specification.where(spec), pageable);
        } else if (filterByAvailability.equals(FilterBy.UNAVAILABLE)) {
            MovieSpecification spec = new MovieSpecification(new SearchCriteria("availability", ":", false));
            moviePage = movieRepository.findAll(Specification.where(spec), pageable);
        } else
            moviePage = movieRepository.findAll(pageable);

        if (sortByLikes) {
            return new MoviePagedList(moviePage
                    .stream()
                    .map(movie -> {
                        MovieDTO movieDTO = movieMapper.movieToMovieDTO(movie);
                        movieDTO.setMovieUrl(getMovieUrl(movie.getIdMovie()));
                        movieDTO.setNumberOfLikes(movie.getLikes().size());
                        return movieDTO;
                    })
                    .sorted(Comparator.comparing(MovieDTO::getNumberOfLikes))
                    .sorted((o1, o2) -> o2.getNumberOfLikes().compareTo(o1.getNumberOfLikes()))
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            moviePage.getPageable().getPageNumber(),
                            moviePage.getPageable().getPageSize()
                    ),
                    moviePage.getTotalElements());
        } else {
            return new MoviePagedList(moviePage
                    .stream()
                    .map(movie -> {
                        MovieDTO movieDTO = movieMapper.movieToMovieDTO(movie);
                        movieDTO.setMovieUrl(getMovieUrl(movie.getIdMovie()));
                        movieDTO.setNumberOfLikes(movie.getLikes().size());
                        return movieDTO;
                    })
                    .collect(Collectors.toList()),
                    PageRequest.of(
                            moviePage.getPageable().getPageNumber(),
                            moviePage.getPageable().getPageSize()
                    ),
                    moviePage.getTotalElements());
        }
    }

    @Override
    public List<MovieDTO> listAll() {
        return movieRepository
                .findAll()
                .stream()
                .map(movie -> {
                    MovieDTO movieDTO = movieMapper.movieToMovieDTO(movie);
                    movieDTO.setMovieUrl(getMovieUrl(movie.getIdMovie()));
                    movieDTO.setNumberOfLikes(movie.getLikes().size());
                    return movieDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO getById(Long id) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        MovieDTO movieDTO = null;
        if (movieOptional.isPresent()) {
            Movie foundMovie = movieOptional.get();
            movieDTO = movieMapper.movieToMovieDTO(foundMovie);
            movieDTO.setMovieUrl(getMovieUrl(id));
            movieDTO.setNumberOfLikes(foundMovie.getLikes().size());
            return movieDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public MovieDTO add(MovieDTO movieDTO) {
        return saveAndReturnDTO(movieMapper.movieDtoToMovie(movieDTO));
    }

    @Override
    public MovieDTO modify(Long id, MovieDTO movieDTO) {
        Movie movie = movieMapper.movieDtoToMovie(movieDTO);
        movie.setIdMovie(id);
        logPossibleMovieUpdates(id, movieDTO);
        return saveAndReturnDTO(movie);
    }

    @Override
    public MovieDTO patchMovie(Long id, MovieDTO movieDTO) {
        logPossibleMovieUpdates(id, movieDTO);
        return movieRepository.findById(id).map(movie -> {

            if (movieDTO.getTitle() != null) {
                movie.setTitle(movieDTO.getTitle());
            }
            if (movieDTO.getDescription() != null) {
                movie.setDescription(movieDTO.getDescription());
            }
            if (movieDTO.getStock() != null) {
                movie.setStock(movieDTO.getStock());
            }
            if (movieDTO.getAvailability() != null) {
                movie.setAvailability(movieDTO.getAvailability());
            }
            if (movieDTO.getRentalPrice() != null) {
                movie.setRentalPrice(movieDTO.getRentalPrice());
            }
            if (movieDTO.getSalePrice() != null) {
                movie.setSalePrice(movieDTO.getSalePrice());
            }
            if (movieDTO.getImageUrl() != null) {
                movie.setImageUrl(movieDTO.getImageUrl());
            }

            MovieDTO returnDto = movieMapper.movieToMovieDTO(movieRepository.save(movie));
            returnDto.setMovieUrl(getMovieUrl(id));
            return returnDto;
        }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public boolean likeAMovie(Long idMovie, Long idUser) {
        if (movieRepository.hasLike(idMovie, idUser) <= 0) {
            movieRepository.likeAMovie(idMovie, idUser);
        }
        return true;
    }

    @Override
    public boolean dislikeAMovie(Long idMovie, Long idUser) {
        movieRepository.dislikeAMovie(idMovie, idUser);
        return true;
    }

    @Override
    public MovieDTO getByName(String movieName) {
        Optional<Movie> movieOptional = movieRepository.findByName(movieName);
        MovieDTO movieDTO = null;
        if (movieOptional.isPresent()) {
            Movie foundMovie = movieOptional.get();
            movieDTO = movieMapper.movieToMovieDTO(foundMovie);
            movieDTO.setMovieUrl(getMovieUrl(foundMovie.getIdMovie()));
            movieDTO.setNumberOfLikes(foundMovie.getLikes().size());
            return movieDTO;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @Override
    public boolean remove(Long id) {
        movieRepository.deleteById(id);
        return true;
    }

    private MovieDTO saveAndReturnDTO(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        MovieDTO returnDto = movieMapper.movieToMovieDTO(savedMovie);
        returnDto.setMovieUrl(getMovieUrl(savedMovie.getIdMovie()));
        return returnDto;
    }

    private String getMovieUrl(Long id) {
        return MovieController.BASE_URL + "/" + id;
    }

    private void logPossibleMovieUpdates(Long id, MovieDTO movieToUpdate) {
        Optional<Movie> movieOptional = movieRepository.findById(id);
        List<MovieUpdateHistory> list = new ArrayList<>();
        if (movieOptional.isPresent()) {
            Movie currentMovie = movieOptional.get();
            if (movieToUpdate.getTitle() != null && !currentMovie.getTitle().equals(movieToUpdate.getTitle())) {
                MovieUpdateHistory movieUpdateHistory = MovieUpdateHistory.builder()
                        .description("\"[" + currentMovie.getIdMovie() + "] - " + currentMovie.getTitle() + "\" movie's title has changed from \""
                                + currentMovie.getTitle() + "\" to \""
                                + movieToUpdate.getTitle() + "\"."
                        )
                        .build();
                list.add(movieUpdateHistory);
            }
            if (movieToUpdate.getRentalPrice() != null && !currentMovie.getRentalPrice().equals(movieToUpdate.getRentalPrice())) {
                MovieUpdateHistory movieUpdateHistory = MovieUpdateHistory.builder()
                        .description("\"[" + currentMovie.getIdMovie() + "] - " + currentMovie.getTitle() + "\" movie's rental price has changed from \""
                                + currentMovie.getRentalPrice() + "\" to \""
                                + movieToUpdate.getRentalPrice() + "\"."
                        )
                        .build();
                list.add(movieUpdateHistory);
            }
            if (movieToUpdate.getSalePrice() != null && !currentMovie.getSalePrice().equals(movieToUpdate.getSalePrice())) {
                MovieUpdateHistory movieUpdateHistory = MovieUpdateHistory.builder()
                        .description("\"[" + currentMovie.getIdMovie() + "] - " + currentMovie.getTitle() + "\" movie's sale price has changed from \""
                                + currentMovie.getSalePrice() + "\" to \""
                                + movieToUpdate.getSalePrice() + "\"."
                        )
                        .build();
                list.add(movieUpdateHistory);
            }
            list.forEach(movieUpdateHistory -> {
                logRepository.save(movieUpdateHistory);
                log.info(movieUpdateHistory.toString());
            });
        }
    }

}
