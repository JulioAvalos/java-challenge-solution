package com.applaudo.movies.web.api.v1.controller;

import com.applaudo.movies.services.PurchaseService;
import com.applaudo.movies.web.api.v1.model.PurchaseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(PurchaseController.BASE_URL)
@RequiredArgsConstructor
public class PurchaseController {

    public static final String BASE_URL = "/api/v1/purchases";

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Object> registerRent(@Valid @RequestBody PurchaseDTO purchaseDTO) {
        boolean isPurchased = purchaseService.buyMovie(purchaseDTO);
        if (isPurchased) {
            return ResponseEntity.ok().build();
        } else
            return ResponseEntity.notFound().build();
    }

}
