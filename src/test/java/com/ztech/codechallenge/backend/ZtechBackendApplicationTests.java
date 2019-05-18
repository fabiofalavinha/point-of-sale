package com.ztech.codechallenge.backend;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ztech.codechallenge.backend.dto.PointOfSaleDTO;
import com.ztech.codechallenge.backend.dto.PointOfSaleSearchDTO;
import com.ztech.codechallenge.backend.util.JsonUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ZtechBackendApplicationTests {

    private static Long POINT_OF_SALE_ID = 60L;

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void getPointOfSaleById() throws Exception {
		Long pointOfSaleId = 1L;
		String uri = "/pdv/{id}";
		MvcResult mvcResult =
			mvc.perform(MockMvcRequestBuilders.get(uri, pointOfSaleId).accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);

		String content = mvcResult.getResponse().getContentAsString();
		PointOfSaleDTO dto = JsonUtils.mapFromJson(content, PointOfSaleDTO.class);
		assertNotNull(dto);
		assertSame(dto.getId(), pointOfSaleId);
	}

	@Test
	//@Ignore
	public void searchByGeoPoint() throws Exception {
        PointOfSaleSearchDTO searchDTO = new PointOfSaleSearchDTO();
        searchDTO.setLatitude(-43.36556);
        searchDTO.setLongitude(-22.99669);

        String requestContent = JsonUtils.mapToJson(searchDTO);

        String uri = "/pdv/search";
        MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestContent)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String content = mvcResult.getResponse().getContentAsString();
        PointOfSaleDTO dto = JsonUtils.mapFromJson(content, PointOfSaleDTO.class);
        assertNotNull(dto);
        assertSame(dto.getId(), 1L);
    }

    @Test
    public void createPointOfSale() throws Exception {
	    String requestContent =
            "{" +
            "        \"id\": " + POINT_OF_SALE_ID++ + ", " +
            "        \"tradingName\": \"Adega da Cerveja - Pinheiros\"," +
            "        \"ownerName\": \"Zé da Silva\"," +
            "        \"document\": \"77.640.880/0001-95\", " +
            "        \"coverageArea\": { " +
            "          \"type\": \"MultiPolygon\", " +
            "          \"coordinates\": [" +
            "            [[[30, 20], [45, 40], [10, 40], [30, 20]]], " +
            "            [[[15, 5], [40, 10], [10, 20], [5, 10], [15, 5]]]" +
            "          ]\n" +
            "        }, " +
            "        \"address\": { " +
            "          \"type\": \"Point\"," +
            "          \"coordinates\": [-46.57421, -21.785741]" +
            "        }" +
            " }";

        String uri = "/pdv";
        MvcResult mvcResult =
            mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(requestContent)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
