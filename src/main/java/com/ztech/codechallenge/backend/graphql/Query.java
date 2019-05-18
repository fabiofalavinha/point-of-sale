package com.ztech.codechallenge.backend.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.parser.DataConverter;
import com.ztech.codechallenge.backend.service.PointOfSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class Query implements GraphQLQueryResolver {

    private final PointOfSaleService pointOfSaleService;
    private final DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter;

    @Autowired
    public Query(PointOfSaleService pointOfSaleService, DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataConverter) {
        this.pointOfSaleService = pointOfSaleService;
        this.pointOfSaleDataConverter = pointOfSaleDataConverter;
    }

    public PointOfSaleDTO findById(int id) {
        return pointOfSaleService.findById((long) id).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public PointOfSaleDTO searchByGeoPoint(double latitude, double longitude) {
        return pointOfSaleService.searchBy(latitude, longitude).map(pointOfSaleDataConverter::convertTo).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
