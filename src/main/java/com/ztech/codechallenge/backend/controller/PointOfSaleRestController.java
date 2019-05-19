package com.ztech.codechallenge.backend.controller;

import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PointOfSaleRestController {

    private final PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate;

    @Autowired
    public PointOfSaleRestController(PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate) {
        this.pointOfSaleBusinessDelegate = pointOfSaleBusinessDelegate;
    }

    @GetMapping("/pdv/{id}")
    public PointOfSaleDTO findById(@PathVariable("id")  Long id) {
        return pointOfSaleBusinessDelegate.findPointOfSaleById(id);
    }

    @PostMapping("/pdv/search")
    public PointOfSaleDTO searchByGeoPoint(@RequestBody PointOfSaleSearchDTO dto) {
        return pointOfSaleBusinessDelegate.searchPointOfSaleByGeoPoint(dto);
    }

    @PostMapping("/pdv")
    public void save(@RequestBody PointOfSaleDTO dto) {
        pointOfSaleBusinessDelegate.savePointOfSale(dto);
    }
}
