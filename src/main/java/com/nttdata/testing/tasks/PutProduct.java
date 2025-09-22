package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Put;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PutProduct implements Task {
    private final String title,price;
    private final String ProductID;

    public PutProduct(String title, String price){
        this.title = title;
        this.price = price;
        this.ProductID = OnStage.theActorInTheSpotlight().recall("ProductID");
    }

    public static Performable fromPage(String title, String price){
        return instrumented(PutProduct.class,title,price);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Put.to("/products/"+ProductID)
                .with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body("{\"title\":"+title+","
                                +"\"price\":"+price+"}")
                        .log().all()));
    }
}
