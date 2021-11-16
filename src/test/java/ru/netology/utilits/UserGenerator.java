package ru.netology.utilits;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class UserGenerator {
    private Faker faker = new Faker(new Locale("en"));
    private String login = faker.name().firstName();
    private String password = faker.internet().password();
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static void setUpUser(RegistrationInfo user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public RegistrationInfo active() {
        RegistrationInfo activeUser = new RegistrationInfo(login, password, "active");
        setUpUser(activeUser);
        return activeUser;
    }

    public RegistrationInfo blocked() {
        RegistrationInfo blockedUser = new RegistrationInfo(login, password, "blocked");
        setUpUser(blockedUser);
        return blockedUser;
    }
}
