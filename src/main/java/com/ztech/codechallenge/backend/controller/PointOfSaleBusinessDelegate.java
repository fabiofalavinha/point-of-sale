package com.ztech.codechallenge.backend.controller;

import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleSearchDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.parser.DataConverter;
import com.ztech.codechallenge.backend.service.PointOfSaleService;
import com.ztech.codechallenge.backend.validator.ValidationResult;
import com.ztech.codechallenge.backend.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class PointOfSaleBusinessDelegate {

    private final PointOfSaleService pointOfSaleService;
    private final DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter;
    private final Validator<PointOfSaleDTO> pointOfSaleDTOValidator;

    @Autowired
    public PointOfSaleBusinessDelegate(PointOfSaleService pointOfSaleService, DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter, Validator<PointOfSaleDTO> pointOfSaleDTOValidator) {
        this.pointOfSaleService = pointOfSaleService;
        this.pointOfSaleDataConverter = pointOfSaleDataConverter;
        this.pointOfSaleDTOValidator = pointOfSaleDTOValidator;
    }

    public void savePointOfSale(PointOfSaleDTO dto) {
        final ValidationResult validationResult = pointOfSaleDTOValidator.validate(dto);
        if (validationResult.hasError()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationResult.getErrorMessage());
        }
        pointOfSaleService.save(pointOfSaleDataConverter.convertFrom(dto));
    }

    public PointOfSaleDTO findPointOfSaleById(Long id) {
        return pointOfSaleService.findById(id).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PointOfSaleDTO searchPointOfSaleByGeoPoint(PointOfSaleSearchDTO dto) {
        return pointOfSaleService.searchBy(dto.getLatitude(), dto.getLongitude()).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
