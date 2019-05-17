package com.ztech.codechallenge.backend.service;

import com.ztech.codechallenge.backend.model.PointOfSale;

import java.util.Optional;

public interface PointOfSaleService {

    Optional<PointOfSale> findById(Long id);
    Optional<PointOfSale> searchBy(double latitude, double longitude);
    void save(PointOfSale pointOfSale);

}
