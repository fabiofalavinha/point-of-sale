package com.ztech.codechallenge.backend.persistence;

import com.vividsolutions.jts.geom.Geometry;
import com.ztech.codechallenge.backend.model.PointOfSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointOfSaleRepository extends CrudRepository<PointOfSale, Long> {

    @Query("select p from PointOfSale p where within(p.coverageArea, :geometry) = true")
    Optional<PointOfSale> searchBy(Geometry geometry);

    boolean existsByDocument(String document);

}
