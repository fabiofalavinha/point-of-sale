package com.ztech.codechallenge.backend.dto;

import org.wololo.geojson.MultiPolygon;
import org.wololo.geojson.Point;

public class PointOfSaleDTO {

    private Long id;
    private String tradingName;
    private String ownerName;
    private String document;
    private Point address;
    private MultiPolygon coverageArea;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public Point getAddress() {
        return address;
    }

    public void setAddress(Point address) {
        this.address = address;
    }

    public MultiPolygon getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(MultiPolygon coverageArea) {
        this.coverageArea = coverageArea;
    }
}
