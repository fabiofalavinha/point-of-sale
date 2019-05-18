package com.ztech.codechallenge.backend.persistence;

import com.vividsolutions.jts.geom.Point;
import com.ztech.codechallenge.backend.model.PointOfSale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PointOfSaleRepository extends CrudRepository<PointOfSale, Long> {

    @Query("select p from PointOfSale p where intersects(:point, p.coverageArea) = true")
    Optional<PointOfSale> searchBy(@Param("point") Point point);

    boolean existsByDocument(String document);

}
