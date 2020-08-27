package com.applaudo.movies.services.impl;

import com.applaudo.movies.domain.Movie;
import com.applaudo.movies.domain.Rental;
import com.applaudo.movies.domain.RentalAndPurchases;
import com.applaudo.movies.domain.User;
import com.applaudo.movies.repositories.MovieRepository;
import com.applaudo.movies.repositories.RentalAndPurchasesRepository;
import com.applaudo.movies.repositories.RentalRepository;
import com.applaudo.movies.repositories.UserRepository;
import com.applaudo.movies.services.RentalService;
import com.applaudo.movies.web.api.v1.model.RentalDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class RentalServiceImpl implements RentalService {

    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RentalAndPurchasesRepository rentalAndPurchasesRepository;

    @Value("${movie.returnDays}")
    private Integer returnDays;

    @Value("${movie.penaltyfee}")
    private BigDecimal penaltyFee;

    @Override
    public boolean rentAMovie(RentalDTO rentalDTO) {
        Optional<Movie> movieOptional = movieRepository.findById(rentalDTO.getIdMovie());
        Optional<User> userOptional = userRepository.findById(rentalDTO.getIdUser());
        if (movieOptional.isPresent() && userOptional.isPresent()) {

            LocalDateTime rentalDate = LocalDateTime.now();

            Rental rental = Rental.builder()
                    .movie(movieOptional.get())
                    .user(userOptional.get())
                    .rentalDate(rentalDate)
                    .penaltyPaid(BigDecimal.ZERO)
                    .returnDate(rentalDate.plusDays(returnDays))
                    .movieReturned(false)
                    .build();

            Rental saved = rentalRepository.save(rental);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            RentalAndPurchases rentalAndPurchases = RentalAndPurchases.builder()
                    .description("The user \"" + userOptional.get().getUsername()
                            + "\" rented \"" + movieOptional.get().getTitle() + "\" at " + formatter.format(rentalDate))
                    .build();
            rentalAndPurchasesRepository.save(rentalAndPurchases);

            log.info(rentalAndPurchases.toString());
            log.info(rental.toString());
            log.info(saved.toString());
            log.info(rentalDTO.toString());

            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean returnMovie(Long id) {
        Optional<Rental> optionalRental = rentalRepository.findById(id);
        if (optionalRental.isPresent()) {
            Rental returnedMovie = optionalRental.get();
            returnedMovie.setMovieReturned(true);
            if (returnedMovie.getReturnDate().isAfter(LocalDateTime.now())) {
                returnedMovie.setPenaltyPaid(penaltyFee);
            }
            rentalRepository.save(returnedMovie);
            return true;
        } else {
            return false;
        }
    }

}
