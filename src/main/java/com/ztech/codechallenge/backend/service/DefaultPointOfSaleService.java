package com.ztech.codechallenge.backend.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.persistence.PointOfSaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
class DefaultPointOfSaleService implements PointOfSaleService {

    private final PointOfSaleRepository pointOfSaleRepository;
    private final GeometryFactory geometryFactory;

    @Autowired
    DefaultPointOfSaleService(PointOfSaleRepository pointOfSaleRepository) {
        this.pointOfSaleRepository = pointOfSaleRepository;
        geometryFactory = new GeometryFactory();
    }

    @Transactional
    public void save(PointOfSale pointOfSale) {
        if (pointOfSaleRepository.existsByDocument(pointOfSale.getDocument())) {
            throw new IllegalStateException(String.format("Point of Sale already registered with document [%s]", pointOfSale.getDocument()));
        }
        pointOfSaleRepository.save(pointOfSale);
    }

    @Transactional
    public Optional<PointOfSale> findById(Long id) {
        return pointOfSaleRepository.findById(id);
    }

    @Transactional
    public Optional<PointOfSale> searchBy(double latitude, double longitude) {
        final Point point = geometryFactory.createPoint(new Coordinate(latitude, longitude));
        return StreamSupport.stream(pointOfSaleRepository.findAll().spliterator(), true).filter(p -> point.intersects(p.getCoverageArea()) || point.touches(p.getCoverageArea())).findFirst();
    }
}
