package com.applaudo.movies.services.impl;

import com.applaudo.movies.domain.Movie;
import com.applaudo.movies.domain.Purchase;
import com.applaudo.movies.domain.RentalAndPurchases;
import com.applaudo.movies.domain.User;
import com.applaudo.movies.repositories.MovieRepository;
import com.applaudo.movies.repositories.PurchaseRepository;
import com.applaudo.movies.repositories.RentalAndPurchasesRepository;
import com.applaudo.movies.repositories.UserRepository;
import com.applaudo.movies.services.PurchaseService;
import com.applaudo.movies.web.api.v1.model.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final RentalAndPurchasesRepository rentalAndPurchasesRepository;

    @Override
    public boolean buyMovie(PurchaseDTO purchaseDTO) {

        Optional<Movie> movieOptional = movieRepository.findById(purchaseDTO.getIdMovie());
        Optional<User> userOptional = userRepository.findById(purchaseDTO.getIdUser());

        if (movieOptional.isPresent() && userOptional.isPresent()) {

            LocalDateTime purchaseDate = LocalDateTime.now();
            Movie updatedMovie = movieOptional.get();

            if (updatedMovie.getStock() > 0) {

                int remainStock = updatedMovie.getStock() - purchaseDTO.getQuantity();

                int moviesBought = 0;

                if (remainStock > 0) {
                    moviesBought = purchaseDTO.getQuantity();
                    updatedMovie.setStock(updatedMovie.getStock() - purchaseDTO.getQuantity());
                } else {
                    moviesBought = updatedMovie.getStock();
                    updatedMovie.setStock(0);
                }

                Purchase purchase = Purchase.builder()
                        .movie(updatedMovie)
                        .user(userOptional.get())
                        .quantity(purchaseDTO.getQuantity())
                        .purchaseDate(purchaseDate)
                        .build();

                Purchase saved = purchaseRepository.save(purchase);

                movieRepository.save(updatedMovie);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                RentalAndPurchases rentalAndPurchases = RentalAndPurchases.builder()
                        .description("The user \"" + userOptional.get().getUsername()
                                + "\" purchased " + moviesBought + " movie copies of \""
                                + movieOptional.get().getTitle() + "\"  at " + formatter.format(purchaseDate))
                        .build();

                rentalAndPurchasesRepository.save(rentalAndPurchases);

                log.info(rentalAndPurchases.toString());
                log.info(purchase.toString());
                log.info(saved.toString());
                log.info(purchaseDTO.toString());
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
