package automation.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PetStoreTests {

    @BeforeMethod
    public void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void updateExistingPet() {
        // Update an existing pet
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
                        "name": "Golden Retriever"
                    }
                ],
                "status": "available"
            }""";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(updatePetJson)
                .when()
                .put("/pet");

        // Assert response
        assertEquals(response.getStatusCode(), 200, "Update pet request failed");
        assertEquals(response.jsonPath().get("name"), "Buddy");
        System.out.println("Update Pet Response: " + response.asString());
    }

    @Test
    public void addNewPet() {
        // Add a new pet
        String newPetJson = """
            {
                "id": 999,
                "category": {
                    "id": 1,
                    "name": "Cats"
                },
                "name": "Whiskers",
                "photoUrls": ["string"],
                "tags": [
                    {
                        "id": 1,
                        "name": "Siamese"
                    }
                ],
                "status": "available"
            }""";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(newPetJson)
                .when()
                .post("/pet");

        // Assert response
        assertEquals(response.getStatusCode(), 200, "Add pet request failed");
        assertEquals(response.jsonPath().get("name"), "Whiskers");
        System.out.println("Add Pet Response: " + response.asString());
    }

    @Test
    public void findPetsByStatus() {
        // Find pets by status
        Response response = given()
                .when()
                .queryParam("status", "available")
                .get("/pet/findByStatus");

        // Assert response
        assertEquals(response.getStatusCode(), 200, "Find pets request failed");
        System.out.println();
        assertEquals(response.jsonPath().get("status"), "available");
        System.out.println("Find Pets Response: " + response.asString());
    }
}