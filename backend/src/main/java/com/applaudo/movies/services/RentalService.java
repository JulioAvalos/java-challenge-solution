package com.applaudo.movies.services;

import com.applaudo.movies.web.api.v1.model.RentalDTO;

public interface RentalService {

    boolean rentAMovie(RentalDTO rentalDTO);

    boolean returnMovie(Long id);

}
