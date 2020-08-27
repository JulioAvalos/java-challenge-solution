package com.applaudo.movies.services;

import com.applaudo.movies.web.api.v1.model.PurchaseDTO;

public interface PurchaseService {
    boolean buyMovie(PurchaseDTO purchaseDTO);
}
