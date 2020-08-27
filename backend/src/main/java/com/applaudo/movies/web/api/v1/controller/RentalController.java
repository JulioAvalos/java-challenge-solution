package com.applaudo.movies.web.api.v1.controller;

import com.applaudo.movies.services.RentalService;
import com.applaudo.movies.web.api.v1.model.RentalDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(RentalController.BASE_URL)
@RequiredArgsConstructor
public class RentalController {

    public static final String BASE_URL = "/api/v1/rentals";

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Object> registerRent(@Valid @RequestBody RentalDTO rentDTO) {
        rentalService.rentAMovie(rentDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/return/{id}")
    public ResponseEntity<Object> returnMovie(@PathVariable Long id) {
        boolean isReturn = rentalService.returnMovie(id);
        if (isReturn) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
