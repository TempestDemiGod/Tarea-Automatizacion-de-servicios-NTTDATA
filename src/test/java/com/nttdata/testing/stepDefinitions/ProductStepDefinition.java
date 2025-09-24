package com.nttdata.testing.stepDefinitions;

import com.nttdata.testing.questions.ResponseCode;
import com.nttdata.testing.tasks.DeleteProduct;
import com.nttdata.testing.tasks.GetAllProducts;
import com.nttdata.testing.tasks.PostProduct;
import com.nttdata.testing.tasks.PutProduct;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.CoreMatchers.equalTo;

public class ProductStepDefinition {

    @Before
    public void setTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("el {actor} establece el endpoint de products")
    public void elActorEstableceElEndpointDeProducts(Actor actor) {
        actor.whoCan(CallAnApi.at("https://fakestoreapi.com"));
    }

    @When("el {actor} realiza la solicitud GET")
    public void elActorRealizaLaSolicitudGET(Actor actor) {
        actor.attemptsTo(GetAllProducts.fromEndpoint("/products"));
    }

    @Then("el codigo de respuesta debe ser {int}")
    public void elCodigoDeRespuestaDebeSer(int responseCode) {
        theActorInTheSpotlight().should(seeThat("El código de respuesta", ResponseCode.getStatus(), equalTo(responseCode) ));
    }

    @When("el actor crea un product con (.*) (.*)$")
    public void elActorCreaUnProductCon(String title, String price) {
        theActorInTheSpotlight().attemptsTo(PostProduct.fromPage(title, price));
    }

    @When("el actor actualiza el producto con los datos (.*) (.*)$")
    public void elActorActualizaElProductoConLosDatos(String title, String price) {
        theActorInTheSpotlight().attemptsTo(PutProduct.fromPage(title,price));
    }

    @When("el {actor} realiza la solicitud Delete")
    public void elActorRealizaLaSolicitudDelete(Actor actor) {
        actor.attemptsTo(DeleteProduct.fromEndpoint("/products"));
    }
}
