package com.ztech.codechallenge.backend.model;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PointOfSale {

    @Id
    private Long id;

    @Column(nullable = false)
    private String tradingName;

    @Column(nullable = false)
    private String ownerName;

    @Column(nullable = false, unique = true)
    private String document;

    @Column(nullable = false)
    private Geometry address;

    @Column(nullable = false)
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

    public Geometry getAddress() {
        return address;
    }

    public void setAddress(Geometry address) {
        this.address = address;
    }

    public MultiPolygon getCoverageArea() {
        return coverageArea;
    }

    public void setCoverageArea(MultiPolygon coverageArea) {
        this.coverageArea = coverageArea;
    }
}
