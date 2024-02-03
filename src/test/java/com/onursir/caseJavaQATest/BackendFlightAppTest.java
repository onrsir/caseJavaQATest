package com.onursir.caseJavaQATest;

import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeAll;

public class BackendFlightAppTest {

	@BeforeAll
	public static void setup() {
		RestAssured.baseURI = "https://flights-api.buraky.workers.dev/";
	}

	@Test
	public void testGetFlights() {
		given().
				when().
				get("/").
				then().
				assertThat().
				statusCode(200).
				and().
				contentType(ContentType.JSON).
				and().
				body("data", hasSize(greaterThan(1))).
				body("data.id", everyItem(isA(Integer.class))).
				body("data.from", everyItem(isA(String.class))).
				body("data.to", everyItem(isA(String.class))).
				body("data.date", everyItem(isA(String.class)));
	}
}


