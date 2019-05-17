package com.ztech.codechallenge.backend.parser;

import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import org.springframework.stereotype.Component;
import org.wololo.geojson.MultiPolygon;
import org.wololo.geojson.Point;
import org.wololo.jts2geojson.GeoJSONReader;
import org.wololo.jts2geojson.GeoJSONWriter;

@Component
public class PointOfSalveConverter implements DataConverter<PointOfSaleDTO, PointOfSale> {

    public PointOfSale convertFrom(PointOfSaleDTO dto) {
        final PointOfSale pointOfSale = new PointOfSale();
        pointOfSale.setId(dto.getId());
        pointOfSale.setTradingName(dto.getTradingName());
        pointOfSale.setOwnerName(dto.getOwnerName());
        pointOfSale.setDocument(dto.getDocument());
        pointOfSale.setAddress(parseAddress(dto.getAddress()));
        pointOfSale.setCoverageArea(parseCoverageArea(dto.getCoverageArea()));
        return pointOfSale;
    }

    @Override
    public PointOfSaleDTO convertTo(PointOfSale pointOfSale) {
        final PointOfSaleDTO dto = new PointOfSaleDTO();
        dto.setId(pointOfSale.getId());
        dto.setTradingName(pointOfSale.getTradingName());
        dto.setOwnerName(pointOfSale.getOwnerName());
        dto.setDocument(pointOfSale.getDocument());
        dto.setAddress((Point) parseGeometry(pointOfSale.getAddress()));
        dto.setCoverageArea((MultiPolygon) parseGeometry(pointOfSale.getCoverageArea()));
        return dto;
    }

    private com.vividsolutions.jts.geom.MultiPolygon parseCoverageArea(MultiPolygon multiPolygon) {
        return (com.vividsolutions.jts.geom.MultiPolygon) new GeoJSONReader().read(multiPolygon);
    }

    private org.wololo.geojson.Geometry parseGeometry(com.vividsolutions.jts.geom.Geometry geometry) {
       return new GeoJSONWriter().write(geometry);
    }

    private com.vividsolutions.jts.geom.Geometry parseAddress(Point point) {
        return new GeoJSONReader().read(new Point(point.getCoordinates()));
    }
}
