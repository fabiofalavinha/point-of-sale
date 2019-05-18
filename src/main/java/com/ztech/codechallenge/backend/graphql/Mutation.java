package com.ztech.codechallenge.backend.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.parser.DataConverter;
import com.ztech.codechallenge.backend.service.PointOfSaleService;
import com.ztech.codechallenge.backend.validator.ValidationResult;
import com.ztech.codechallenge.backend.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.wololo.geojson.MultiPolygon;
import org.wololo.geojson.Point;

import java.io.IOException;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final PointOfSaleService pointOfSaleService;
    private final DataConverter<PointOfSaleDTO, PointOfSale> dtoPointOfSaleDataConverter;
    private final Validator<PointOfSaleDTO> pointOfSaleDTOValidator;

    @Autowired
    public Mutation(PointOfSaleService pointOfSaleService, DataConverter<PointOfSaleDTO, PointOfSale> dtoPointOfSaleDataConverter, Validator<PointOfSaleDTO> pointOfSaleDTOValidator) {
        this.pointOfSaleService = pointOfSaleService;
        this.dtoPointOfSaleDataConverter = dtoPointOfSaleDataConverter;
        this.pointOfSaleDTOValidator = pointOfSaleDTOValidator;
    }

    public PointOfSaleDTO createPointOfSale(int id, String tradingName, String ownerName, String document, String address, String coverageArea) {
        final PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setId((long) id);
        dto.setTradingName(tradingName);
        dto.setOwnerName(ownerName);
        dto.setDocument(document);
        dto.setAddress(mapFromJson(address, Point.class));
        dto.setCoverageArea(mapFromJson(coverageArea, MultiPolygon.class));

        final ValidationResult validationResult = pointOfSaleDTOValidator.validate(dto);
        if (validationResult.hasError()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, validationResult.getErrorMessage());
        }

        final PointOfSale pointOfSale = dtoPointOfSaleDataConverter.convertFrom(dto);
        pointOfSaleService.save(pointOfSale);

        return dto;
    }

    private <T> T mapFromJson(String json, Class<T> clazz) throws IllegalArgumentException {
        try {
            return new ObjectMapper().readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
