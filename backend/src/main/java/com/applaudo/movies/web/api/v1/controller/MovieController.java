package com.applaudo.movies.web.api.v1.controller;

import com.applaudo.movies.services.MovieService;
import com.applaudo.movies.util.FilterBy;
import com.applaudo.movies.web.api.v1.model.MovieDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(MovieController.BASE_URL)
@RequiredArgsConstructor
@Slf4j
public class MovieController {

    public static final String BASE_URL = "/api/v1/movies";
    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    private final MovieService service;

    @GetMapping
    public ResponseEntity<Page<MovieDTO>> getListofMovies(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                          @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                          @RequestParam(value = "filter", required = false) String availability,
                                                          @RequestParam(value = "sort", required = false) String sortBy) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdminRole = authentication.getAuthorities().stream().anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if (pageNumber == null || pageNumber < 0) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        boolean sortByLikes = false;
        if (sortBy != null && sortBy.length() > 0) {
            String[] sortingKeywords = sortBy.trim().split(",");
            for (String sortTest : sortingKeywords) {
                if (sortTest.equals("likes")) {
                    sortByLikes = true;
                    break;
                }
            }
        }

        FilterBy isAvailable = FilterBy.AVAILABLE;

        if (availability != null && availability.trim().equals("availability") && hasAdminRole) {
            isAvailable = FilterBy.AVAILABLE;
        } else if (availability != null && availability.trim().equals("unavailability") && hasAdminRole) {
            isAvailable = FilterBy.UNAVAILABLE;
        } else if (hasAdminRole) {
            isAvailable = FilterBy.NONE;
        }

        Page<MovieDTO> movieList = null;

        if (sortByLikes) {
            movieList = service.pageableMovieList(PageRequest.of(pageNumber, pageSize), sortByLikes, isAvailable);
        } else {
            movieList = service.pageableMovieList(PageRequest.of(pageNumber, pageSize, Sort.by("title")), sortByLikes, isAvailable);
        }


        return new ResponseEntity<>(movieList, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<MovieDTO> searchByName(@RequestParam(value = "name", required = false) String movieName) {
        MovieDTO movieDTO = service.getByName(movieName);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movieDTO = service.getById(id);
        return new ResponseEntity<>(movieDTO, HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("@authServiceImpl.hasAccess('listRoles')")
    public ResponseEntity<MovieDTO> createNewMovie(@RequestBody MovieDTO movieDto) {
        MovieDTO movieDTO = service.add(movieDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("{url}")
                .buildAndExpand(movieDTO.getMovieUrl())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PostMapping("/like/{idMovie}/{idUser}")
    public ResponseEntity<MovieDTO> movieLike(@PathVariable("idMovie") Long idMovie, @PathVariable("idUser") Long idUser) {
        service.likeAMovie(idMovie, idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/dislike/{idMovie}/{idUser}")
    public ResponseEntity<MovieDTO> movieDislike(@PathVariable("idMovie") Long idMovie, @PathVariable("idUser") Long idUser) {
        service.dislikeAMovie(idMovie, idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@authServiceImpl.hasAccess('listRoles')")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO updatedMovie = service.modify(id, movieDTO);
        return new ResponseEntity<>(updatedMovie, HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("@authServiceImpl.hasAccess('listRoles')")
    public ResponseEntity<MovieDTO> patchMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO updatedMovie = service.patchMovie(id, movieDTO);
        return new ResponseEntity<>(updatedMovie, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@authServiceImpl.hasAccess('listRoles')")
    public ResponseEntity<Object> eliminar(@PathVariable Long id) {
        service.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
