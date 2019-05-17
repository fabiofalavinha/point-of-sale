package com.ztech.codechallenge.backend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztech.codechallenge.backend.dto.PointOfSaleCollectionDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.model.PointOfSale;
import com.ztech.codechallenge.backend.parser.DataConverter;
import com.ztech.codechallenge.backend.service.PointOfSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.Objects;

@SpringBootApplication
public class ZtechBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZtechBackendApplication.class, args);
	}

	@Autowired
    private PointOfSaleService pointOfSaleService;

	@Autowired
	private DataConverter<PointOfSaleDTO, PointOfSale> pointOfSaleDataParser;

	@Bean
    CommandLineRunner loadPointOfSalesJson() {
		return args -> {
			final File file = new File(
				Objects.requireNonNull(ZtechBackendApplication.class.getClassLoader().getResource("pdvs.json")).getFile()
			);

			final ObjectMapper objectMapper = new ObjectMapper();
            final PointOfSaleCollectionDTO pointOfSaleCollection = objectMapper.readValue(file, PointOfSaleCollectionDTO.class);

            pointOfSaleCollection.getPointOfSales().forEach(dto -> {
                final PointOfSale pointOfSale = pointOfSaleDataParser.convertFrom(dto);
                pointOfSaleService.save(pointOfSale);
            });
		} ;
	}
}
