package com.ztech.codechallenge.backend.controller;

import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleSearchDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.parser.DataConverter;
import com.ztech.codechallenge.backend.service.PointOfSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class PointOfSaleController {

    private final PointOfSaleService pointOfSaleService;
    private final DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter;

    @Autowired
    public PointOfSaleController(PointOfSaleService pointOfSaleService, DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter) {
        this.pointOfSaleService = pointOfSaleService;
        this.pointOfSaleDataConverter = pointOfSaleDataConverter;
    }

    @GetMapping("/pdv/{id}")
    public PointOfSaleDTO findById(Long id) {
        return pointOfSaleService.findById(id).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/pdv/search")
    public PointOfSaleDTO searchByGeoPoint(
        @RequestBody PointOfSaleSearchDTO dto) {
        return pointOfSaleService.searchBy(dto.getLatitude(), dto.getLongitude()).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/pdv")
    public void save(@RequestBody PointOfSaleDTO dto) {
        pointOfSaleService.save(pointOfSaleDataConverter.convertFrom(dto));
    }
}
