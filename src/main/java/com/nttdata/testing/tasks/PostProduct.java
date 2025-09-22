package com.nttdata.testing.tasks;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.interactions.Post;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostProduct implements Task {
    private final String title, price;

    public PostProduct(String title, String price){
        this.title = title;
        this.price = price;
    }

    public static Performable fromPage(String title, String price){
        return instrumented(PostProduct.class, title, price);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(Post.to("/products")
                .with(requestSpecification -> requestSpecification
                        .contentType(ContentType.JSON)
                        .header("Content-Type","application/json")
                        .header("Accept","application/json")
                        .body("{\"title\":"+title+","
                                +"\"price\":"+price+"}")
                        .log().all()));

        SerenityRest.lastResponse().body().prettyPrint();
        if(SerenityRest.lastResponse().statusCode() == 201){
            OnStage.theActorInTheSpotlight().remember("ProductID", SerenityRest.lastResponse().path("id").toString());
            String valorDelProductID = actor.recall("ProductID");
            System.out.println("Product ID: "+ valorDelProductID);
        }
    }
}
