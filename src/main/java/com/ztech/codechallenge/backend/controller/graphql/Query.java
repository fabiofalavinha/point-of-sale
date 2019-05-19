package com.ztech.codechallenge.backend.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.ztech.codechallenge.backend.controller.PointOfSaleBusinessDelegate;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Query implements GraphQLQueryResolver {

    private final PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate;

    @Autowired
    public Query(PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate) {
        this.pointOfSaleBusinessDelegate = pointOfSaleBusinessDelegate;
    }

    public PointOfSaleDTO findById(int id) {
        return pointOfSaleBusinessDelegate.findPointOfSaleById((long) id);
    }

    public PointOfSaleDTO searchByGeoPoint(double latitude, double longitude) {
        final PointOfSaleSearchDTO dto = new PointOfSaleSearchDTO();
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        return pointOfSaleBusinessDelegate.searchPointOfSaleByGeoPoint(dto);
    }
}
