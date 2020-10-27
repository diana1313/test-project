package apisteps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.Assert;
import pet.store.api.dto.PetBody;
import pet.store.api.dto.PetResponse;
import pet.store.api.other.PetContext;
import pet.store.api.specifiactions.InitSpecification;

import static io.restassured.RestAssured.given;


public class PetsApiStepsDef {
    private PetContext context;

    public PetsApiStepsDef(PetContext context) {
        this.context = context;
    }

    @When("^I create pet with name parameter \"?([^\"]*)\"?$")
    public void iCreatePatWithNameParameter(String parameter) {
        PetResponse postPetResponse = given()
                .spec(InitSpecification.basePetSpecification)
                .body(PetBody.builder()
                        .name(parameter)
                        .build())
                .post()
                .then()
                .extract()
                .as(PetResponse.class);
        context.setId(postPetResponse.getId());
        context.setName(parameter);
    }

    @When("^I update current pet with new name parameter \"?([^\"]*)\"?$")
    public void iUpdateCurrentPetWithNewNameParameter(String parameter) {
        given()
                .spec(InitSpecification.basePetSpecification)
                .body(PetBody.builder()
                        .id(context.getId())
                        .name(parameter)
                        .build())
                .post()
                .then()
                .statusCode(200);
        context.setName(parameter);
    }

    @When("^I delete current pet$")
    public void iDeleteCurrentPet() {
        given()
                .spec(InitSpecification.basePetSpecification)
                .body(PetBody.builder()
                        .id(context.getId())
                        .build())
                .delete()
                .prettyPrint();
    }

    @Then("^I check current pet doesn't exist$")
    public void iCheckCurrentPetDoesNotExist() {
        given()
                .spec(InitSpecification.basePetSpecification)
                .basePath(String.format("/%s", context.getId()))
                .get()
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Then("^I check (?:created|updated) pet exists and has expected name$")
    public void iCheckCreatedPetExistsAndHasExpectedName() {
        PetResponse petResponse = given()
                .spec(InitSpecification.basePetSpecification)
                .basePath(String.format("/%s", context.getId()))
                .get()
                .then()
                .extract()
                .as(PetResponse.class);
        String expectedName = context.getName();
        String actualName = petResponse.getName();
        Assert.assertEquals(expectedName, actualName,
                String.format("Actual name '%s' doesn't match expected '%s'", actualName, expectedName));
    }

}
