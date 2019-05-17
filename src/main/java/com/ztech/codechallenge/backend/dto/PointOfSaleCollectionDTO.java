package com.ztech.codechallenge.backend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PointOfSaleCollectionDTO {

    @JsonProperty("pdvs")
    private List<PointOfSaleDTO> pointOfSales;

    public List<PointOfSaleDTO> getPointOfSales() {
        return pointOfSales;
    }

    public void setPointOfSales(List<PointOfSaleDTO> pointOfSales) {
        this.pointOfSales = pointOfSales;
    }
}
