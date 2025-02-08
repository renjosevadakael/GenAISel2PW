package automation.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.testng.Assert.assertEquals;

public class PetStoreAPITests {

    @BeforeMethod
    public void setUp() {
        // Set base URI for all tests
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void updateExistingPet() {
        // Update an existing pet using PUT method
        String updatePetJson = """
            {
                "id": 1,
                "category": {
                    "id": 1,
                    "name": "Dogs"
                },
                "name": "Buddy",
                "photoUrls": ["string"],
                "tags": [
                    {
                        "id": 1,
                        "name": "tag"
                    }
                ],
                "status": "available"
            }""";

        given()
                .body(updatePetJson)
                .when()
                .put("/pet")
                .then()
                .statusCode(200);

        System.out.println("Updated Pet Response: " + given().when().put("/pet").getBody().asString());
    }

    @Test
    public void addNewPet() {
        // Add a new pet using POST method
        String newPetJson = """
            {
                "id": 999,
                "category": {
                    "id": 1,
                    "name": "Dogs"
                },
                "name": "Max",
                "photoUrls": ["string"],
                "tags": [
                    {
                        "id": 1,
                        "name": "tag"
                    }
                ],
                "status": "available"
            }""";

        given()
                .body(newPetJson)
                .when()
                .post("/pet")
                .then()
                .statusCode(200);

        System.out.println("New Pet Response: " + given().when().post("/pet").getBody().asString());
    }

    @Test
    public void findPetsByStatus() {
        // Find pets by status using GET method
        String status = "available";

        given()
                .when()
                .queryParam("status", status)
                .get("/pet/findByStatus")
                .then()
                .statusCode(200);

        System.out.println("Pets by Status Response: " + 
                given()
                .queryParam("status", status)
                .when()
                .get("/pet/findByStatus")
                .getBody().asString());
    }
}