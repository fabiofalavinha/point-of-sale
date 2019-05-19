package com.ztech.codechallenge.backend.controller.graphql;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.ztech.codechallenge.backend.controller.PointOfSaleBusinessDelegate;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wololo.geojson.MultiPolygon;
import org.wololo.geojson.Point;

import java.io.IOException;

@Component
public class Mutation implements GraphQLMutationResolver {

    private final PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate;

    @Autowired
    public Mutation(PointOfSaleBusinessDelegate pointOfSaleBusinessDelegate) {
        this.pointOfSaleBusinessDelegate = pointOfSaleBusinessDelegate;
    }

    public PointOfSaleDTO createPointOfSale(int id, String tradingName, String ownerName, String document, String address, String coverageArea) throws IOException {
        final PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setId((long) id);
        dto.setTradingName(tradingName);
        dto.setOwnerName(ownerName);
        dto.setDocument(document);
        dto.setAddress(JsonUtils.mapFromJson(address, Point.class));
        dto.setCoverageArea(JsonUtils.mapFromJson(coverageArea, MultiPolygon.class));

        pointOfSaleBusinessDelegate.savePointOfSale(dto);

        return dto;
    }
}
