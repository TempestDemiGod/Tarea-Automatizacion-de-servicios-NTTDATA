package com.nttdata.testing.tasks;

import io.cucumber.messages.types.Product;
import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Get;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class DeleteProduct implements Task {
    private final String endpoint;
    private final String ProductID;

    public DeleteProduct(String endpoint , String NewEndpoint){
        this.endpoint = endpoint+"/{productId}";
        this.ProductID = OnStage.theActorInTheSpotlight().recall("ProductID");

    }

    public static Performable fromEndpoint(String NewEndpoint){
        return instrumented(GetAllProducts.class, NewEndpoint);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Get.resource(endpoint)
                        .with(requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .log().all()
                                .pathParams("productId",ProductID))
        );
    }
}
